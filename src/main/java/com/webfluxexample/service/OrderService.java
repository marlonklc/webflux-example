package com.webfluxexample.service;

import com.webfluxexample.dto.OrderBuilder;
import com.webfluxexample.dto.OrderDto;
import com.webfluxexample.entity.Order;
import com.webfluxexample.exception.OrderNotFoundException;
import com.webfluxexample.repository.OrderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderService {

    private FoodService foodService;
    private CustomerService customerService;
    private OrderRepository repository;

    public OrderService(FoodService foodService, CustomerService customerService, OrderRepository repository) {
        this.foodService = foodService;
        this.customerService = customerService;
        this.repository = repository;
    }

    public Mono<Void> save(Order order) {
        return Mono.zip(
            foodService.find(order.getIdFood()),
            customerService.find(order.getIdCustomer())
        )
        .flatMap(tuple -> repository.save(order))
        .then();
    }

    public Mono<OrderDto> getOrder(String orderId) {
        return repository.findById(orderId)
            .switchIfEmpty(Mono.error(new OrderNotFoundException()))
            .flatMap(order -> composeDto(order.getIdFood(), order.getIdCustomer()));
    }

    public Mono<Long> count() {
        return repository.count();
    }

    private Mono<OrderDto> composeDto(String idFood, String idCustomer) {
        return Mono.just(new OrderBuilder())
            .zipWith(foodService.find(idFood), OrderBuilder::food)
            .zipWith(customerService.find(idCustomer), OrderBuilder::customer)
            .map(OrderBuilder::build);
    }

    public Flux<OrderDto> findAllByIdCustomer(String customerId) {
        return repository.findAllByIdCustomer(customerId)
            .flatMap(a -> composeDto(a.getIdFood(), a.getIdCustomer()));
    }
}
