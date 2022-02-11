package com.webfluxexample.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webfluxexample.api.request.OrderRequest;
import com.webfluxexample.api.response.OrderResponse;
import com.webfluxexample.entity.Order;
import com.webfluxexample.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
public class OrderApi {

    private OrderService service;
    private ObjectMapper objectMapper;

    public OrderApi(OrderService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> create(@RequestBody OrderRequest request) {
        return Mono.just(objectMapper.convertValue(request, Order.class))
            .flatMap(order -> service.save(order))
            .then();
    }

    @GetMapping("/{id}")
    public Mono<OrderResponse> get(@PathVariable String id) {
        return service.getOrder(id)
            .map(order -> objectMapper.convertValue(order, OrderResponse.class));
    }
}
