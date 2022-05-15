package com.webfluxexample.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webfluxexample.api.response.FoodResponse;
import com.webfluxexample.service.FoodService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/foods")
public class FoodApi {

    private FoodService service;
    private ObjectMapper objectMapper;

    public FoodApi(FoodService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<FoodResponse> getAll() {
        return service.findAll()
            .map(food -> objectMapper.convertValue(food, FoodResponse.class));
    }

    @GetMapping("/{id}")
    public Mono<FoodResponse> get(@PathVariable String id) {
        return service.find(id)
            .map(food -> objectMapper.convertValue(food, FoodResponse.class));
    }
}
