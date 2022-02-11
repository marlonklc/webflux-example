package com.webfluxexample.api.response;

import com.webfluxexample.entity.Customer;
import com.webfluxexample.entity.Food;

public class OrderResponse {

    private Food food;
    private Customer customer;

    public Food getFood() {
        return food;
    }

    public Customer getCustomer() {
        return customer;
    }

}
