package com.advanced.backend.reactive.repository;

import com.advanced.backend.reactive.model.Sport;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SportRepository extends ReactiveCrudRepository<Sport, Integer> {
    Mono<Boolean> existsByNameIgnoreCase(String name);
    Flux<Sport> findByNameContainingIgnoreCase(String q);

    // Custom insert to honor unique constraint; alternative to save() when we want to return conflict
    @Query("INSERT INTO sport(name) VALUES(:name) ON CONFLICT DO NOTHING")
    Mono<Void> insertIfNotExists(String name);
}
