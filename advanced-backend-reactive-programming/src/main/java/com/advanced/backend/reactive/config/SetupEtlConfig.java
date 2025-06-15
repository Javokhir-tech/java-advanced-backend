package com.advanced.backend.reactive.config;

import com.advanced.backend.reactive.model.Sport;
import com.advanced.backend.reactive.repository.SportRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class SetupEtlConfig {

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://webservice.rakuten.co.jp/explorer/api/IchibaItem/Search")
            .build();

    @Bean
    ApplicationRunner etlRunner(SportRepository repo) {
        return args ->
                webClient.get()
                        // NOTE: real API requires auth key; using sample params here
                        .uri(uri -> uri.queryParam("keyword", "sport").build())
                        .retrieve()
                        .bodyToMono(JsonNode.class)
                        .flatMapMany(json -> Mono.justOrEmpty(json.get("Items")).flatMapMany(Flux::fromIterable))
                        .map(item -> item.get("Item").get("genreName").asText()) // pick any field as sport name
                        .map(String::trim)
                        .distinct()
                        .flatMap(name -> repo.existsByNameIgnoreCase(name)
                                .filter(exists -> !exists)
                                .flatMapMany(exists -> repo.save(new Sport(name))).then()
                        )
                        .then()
                        .subscribe();
    }
}

