package com.webfluxexample.dto;

import com.webfluxexample.entity.Customer;
import com.webfluxexample.entity.Food;

public class OrderDto {

    private Food food;
    private Customer customer;

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
