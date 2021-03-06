package com.nurkiewicz.webflux.demo.consumer.service;


import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

public interface EventStreamWriter {
    final String BATCH_SEPARATOR = "\n";

    /**
     * Writes batch to stream
     *
     * @param os     Stream to write to
     * @param cursor Cursor associated with this branch
     * @param events Events in batch
     * @return count of bytes written
     */
//    int writeBatch(OutputStream os, Cursor cursor, List<byte[]> events) throws IOException;
//
//    int writeSubscriptionBatch(OutputStream os, SubscriptionCursor cursor, List<ConsumedEvent> events,
//                               Optional<String> metadata) throws IOException;
//
}
