package com.webfluxexample.service;

import com.webfluxexample.dto.OrderDto;
import com.webfluxexample.dto.SummaryCustomerDto;
import com.webfluxexample.entity.Customer;
import com.webfluxexample.entity.Food;
import com.webfluxexample.exception.CustomerNotFoundException;
import com.webfluxexample.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private CustomerRepository repository;
    private OrderService orderService;

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Mono<Customer> find(String id) {
        return repository.findById(id)
            .switchIfEmpty(Mono.error(new CustomerNotFoundException()));
    }

    public Mono<Long> count() {
        return repository.count();
    }

    public Flux<OrderDto> getOrdersByCustomer(String customerId) {
        return orderService.findAllByIdCustomer(customerId);
    }

    public Mono<SummaryCustomerDto> getSummaryOrdersByCustomer(String customerId) {
        return orderService.findAllByIdCustomer(customerId)
            .collectList()
            .flatMap(ordersFromCustomer -> {
                Map<String, Long> foodsGroupingByCount = ordersFromCustomer.stream()
                    .map(OrderDto::getFood)
                    .collect(Collectors.groupingBy(Food::getName, Collectors.counting()));

                Map.Entry<String, Long> foodMostOrdered = foodsGroupingByCount.entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue())
                    .orElseGet(() -> new AbstractMap.SimpleEntry<>("<not found>", 0L));

                int totalOrders = ordersFromCustomer.size();

                return Mono.just(new SummaryCustomerDto(foodMostOrdered.getKey(), totalOrders));
            });
    }
}
