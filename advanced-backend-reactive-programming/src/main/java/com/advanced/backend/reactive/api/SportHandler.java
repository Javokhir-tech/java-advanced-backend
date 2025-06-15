package com.advanced.backend.reactive.api;

import com.advanced.backend.reactive.model.Sport;
import com.advanced.backend.reactive.repository.SportRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class SportHandler {
    private final SportRepository repo;

    public SportHandler(SportRepository repo) { this.repo = repo; }

    // POST /api/v1/sport/{sportname}
    public Mono<ServerResponse> createSport(ServerRequest request) {
        String name = request.pathVariable("sportname");
        return repo.existsByNameIgnoreCase(name)
                .flatMap(exists -> {
                    if (exists) {
                        return ServerResponse.status(HttpStatus.CONFLICT)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue("Sport with name '" + name + "' already exists");
                    }
                    return repo.save(new Sport(name))
                            .flatMap(saved -> ServerResponse.status(HttpStatus.CREATED)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .bodyValue(saved));
                });
    }

    // GET /api/v1/sport?q=...
    public Mono<ServerResponse> searchSports(ServerRequest request) {
        String query = request.queryParam("q").orElse("");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(repo.findByNameContainingIgnoreCase(query), Sport.class);
    }
}
