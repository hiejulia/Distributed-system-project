package com.nurkiewicz.webflux.demo.producer;

import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Charsets;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



import java.nio.file.AccessDeniedException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.status;
import static org.springframework.web.bind.annotation.RequestMethod.POST;



@RestController
public class EventProducer {


    @Autowired
    public EventProducer() {

    }

    // Create 1 event
    @RequestMapping(value = "/event-types/{eventTypeName}/events", method = POST)
    public ResponseEntity postEvents(@PathVariable final String eventTypeName,
                                     @RequestBody final String eventsAsString,
                                     )
            throws Exception {
        return postEventsWithMetrics(eventTypeName, eventsAsString, request, client, false);

    }

    // Delete 1 event
    @RequestMapping(value = "/event-types/{eventTypeName}/deleted-events", method = POST)
    public ResponseEntity deleteEvents(@PathVariable final String eventTypeName,
                                       @RequestBody final String eventsAsString,
                                       final HttpServletRequest request,
                                       final Client client) {
        return postEventsWithMetrics(eventTypeName, eventsAsString, request, client, true);

    }

    private ResponseEntity postEventsWithMetrics(final String eventTypeName,
                                                 final String eventsAsString,
                                                 final HttpServletRequest request,
                                                 final Client client,
                                                 final boolean delete) {
        if (blacklistService.isProductionBlocked(eventTypeName, client.getClientId())) {
            throw new BlockedException("Application or event type is blocked");
        }
        final EventTypeMetrics eventTypeMetrics = eventTypeMetricRegistry.metricsFor(eventTypeName);
        try {
            // Create event main service
            final ResponseEntity response = postEventInternal(
                    eventTypeName, eventsAsString, eventTypeMetrics, client, request, delete);
            // Add to metrics count
            eventTypeMetrics.incrementResponseCount(response.getStatusCode().value());
            return response;
        } catch (final NoSuchEventTypeException exception) {
            eventTypeMetrics.incrementResponseCount(NOT_FOUND.getStatusCode());
            throw exception;
        } catch (final RuntimeException ex) {
            eventTypeMetrics.incrementResponseCount(INTERNAL_SERVER_ERROR.getStatusCode());
            throw ex;
        }
    }


    private ResponseEntity postEventInternal(final String eventTypeName,
                                             final String eventsAsString,
                                             final EventTypeMetrics eventTypeMetrics,
                                             final Client client,
                                             final HttpServletRequest request,
                                             final boolean delete)
            throws AccessDeniedException, ServiceTemporarilyUnavailableException, InternalNakadiException,
            EventTypeTimeoutException, NoSuchEventTypeException {
        // start
        final long startingNanos = System.nanoTime();
        try
        {
            // get event byte
            final int totalSizeBytes = eventsAsString.getBytes(Charsets.UTF_8).length;
            // publish event
            final Span publishingSpan = TracingService.extractSpan(request, "publish_events")
                    .setTag("event_type", eventTypeName)
                    .setTag("slo_bucket", TracingService.getSLOBucket(totalSizeBytes))
                    .setTag(Tags.SPAN_KIND_PRODUCER, client.getClientId());

            final EventPublishResult result;
            if (delete) {
                // delete event
                result = publisher.delete(eventsAsString, eventTypeName, publishingSpan);
            } else {
                // publish event as String
                result = publisher.publish(eventsAsString, eventTypeName, publishingSpan);
            }

            final int eventCount = result.getResponses().size();

            // Add to metrics service
            reportMetrics(eventTypeMetrics, result, totalSizeBytes, eventCount);
            reportSLOs(startingNanos, totalSizeBytes, eventCount, result, eventTypeName, client);

            return response(result);
        } finally {
            eventTypeMetrics.updateTiming(startingNanos, System.nanoTime());
        }
    }

    private void reportSLOs(final long startingNanos, final int totalSizeBytes, final int eventCount,
                            final EventPublishResult eventPublishResult, final String eventTypeName,
                            final Client client) {
        if (eventPublishResult.getStatus() == EventPublishingStatus.SUBMITTED) {
            final long msSpent = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startingNanos);
            final String applicationName = client.getClientId();

            nakadiKpiPublisher.publish(kpiBatchPublishedEventType, () -> new JSONObject()
                    .put("event_type", eventTypeName)
                    .put("app", applicationName)
                    .put("app_hashed", nakadiKpiPublisher.hash(applicationName))
                    .put("token_realm", client.getRealm())
                    .put("number_of_events", eventCount)
                    .put("ms_spent", msSpent)
                    .put("batch_size", totalSizeBytes));
        }
    }

    // Add to Metrics table
    private void reportMetrics(final EventTypeMetrics eventTypeMetrics, final EventPublishResult result,
                               final int totalSizeBytes, final int eventCount) {
        if (result.getStatus() == EventPublishingStatus.SUBMITTED) {
            eventTypeMetrics.reportSizing(eventCount, totalSizeBytes - eventCount - 1);
        } else if (result.getStatus() == EventPublishingStatus.FAILED && eventCount != 0) {
            final int successfulEvents = result.getResponses()
                    .stream()
                    .filter(r -> r.getPublishingStatus() == EventPublishingStatus.SUBMITTED)
                    .collect(Collectors.toList())
                    .size();
            final double avgEventSize = totalSizeBytes / (double) eventCount;
            eventTypeMetrics.reportSizing(successfulEvents, (int) Math.round(avgEventSize * successfulEvents));
        }
    }

    private ResponseEntity response(final EventPublishResult result) {
        switch (result.getStatus()) {
            case SUBMITTED:
                return status(HttpStatus.OK).build();
            case ABORTED:
                return status(HttpStatus.UNPROCESSABLE_ENTITY).body(result.getResponses());
            default:
                return status(HttpStatus.MULTI_STATUS).body(result.getResponses());
        }
    }

}
