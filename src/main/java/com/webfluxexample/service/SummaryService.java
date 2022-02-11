package com.webfluxexample.service;

import com.webfluxexample.api.response.SummaryResponse;
import com.webfluxexample.dto.SummaryBuilder;
import com.webfluxexample.dto.SummaryDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SummaryService {

    private OrderService orderService;
    private CustomerService customerService;
    private FoodService foodService;

    public SummaryService(OrderService orderService, CustomerService customerService, FoodService foodService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.foodService = foodService;
    }

    public Mono<SummaryDto> getSummary() {
        return Mono.just(new SummaryBuilder())
            .zipWith(orderService.count(), SummaryBuilder::totalOrders)
            .zipWith(foodService.count(), SummaryBuilder::totalFoods)
            .zipWith(customerService.count(), SummaryBuilder::totalCustomers)
            .map(SummaryBuilder::build);
    }
}
