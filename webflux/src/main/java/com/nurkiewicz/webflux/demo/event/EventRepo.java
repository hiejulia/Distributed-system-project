package com.nurkiewicz.webflux.demo.event;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.net.URI;

public interface EventRepo extends ReactiveMongoRepository<Event, URI> {
}
