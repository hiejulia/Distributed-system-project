package com.nurkiewicz.webflux.demo.event;

import org.springframework.data.annotation.Id;

import java.net.URI;
import java.time.Instant;

public class Event {

    @Id
    private final URI link;

    private final Instant publishedDate;
    private final String name;

    public URI getLink() {
        return link;
    }

    public Instant getPublishedDate() {
        return publishedDate;
    }

    public String getName() {
        return name;
    }

    public Event(URI link, Instant publishedDate, String name) {
        this.link = link;
        this.publishedDate = publishedDate;
        this.name = name;
    }
}
