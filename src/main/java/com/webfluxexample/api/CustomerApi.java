package com.webfluxexample.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webfluxexample.api.response.OrderResponse;
import com.webfluxexample.api.response.SummaryCustomerResponse;
import com.webfluxexample.service.CustomerService;
import com.webfluxexample.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
public class CustomerApi {

    private CustomerService customerService;
    private ObjectMapper objectMapper;

    public CustomerApi(CustomerService customerService, OrderService orderService, ObjectMapper objectMapper) {
        this.customerService = customerService;
        this.customerService.setOrderService(orderService);
        this.objectMapper = objectMapper;
    }

    @GetMapping("/{id}/orders")
    public Flux<OrderResponse> getOrdersByCustomer(@PathVariable("id") String customerId) {
        return customerService.getOrdersByCustomer(customerId)
            .map(order -> objectMapper.convertValue(order, OrderResponse.class));
    }

    @GetMapping("/{id}/summary")
    public Mono<SummaryCustomerResponse> getSummaryOrdersByCustomer(@PathVariable("id") String customerId) {
        return customerService.getSummaryOrdersByCustomer(customerId)
            .map(summary -> objectMapper.convertValue(summary, SummaryCustomerResponse.class));
    }
}
