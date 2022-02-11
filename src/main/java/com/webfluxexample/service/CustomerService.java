package com.webfluxexample.service;

import com.webfluxexample.entity.Customer;
import com.webfluxexample.exception.CustomerNotFoundException;
import com.webfluxexample.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    private CustomerRepository repository;

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
}
