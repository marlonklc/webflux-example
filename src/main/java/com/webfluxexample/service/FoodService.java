package com.webfluxexample.service;

import com.webfluxexample.entity.Food;
import com.webfluxexample.exception.FoodNotFoundException;
import com.webfluxexample.repository.FoodRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FoodService {

    private FoodRepository repository;

    public FoodService(FoodRepository repository) {
        this.repository = repository;
    }

    public Flux<Food> findAll() {
        return repository.findAll();
    }

    public Mono<Long> count() {
        return repository.count();
    }

    public Mono<Food> find(String id) {
        return repository.findById(id)
            .switchIfEmpty(Mono.error(new FoodNotFoundException()));
    }
}
