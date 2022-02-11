package com.webfluxexample.dto;

import com.webfluxexample.entity.Customer;
import com.webfluxexample.entity.Food;
import com.webfluxexample.entity.Order;

public class OrderBuilder {

    private Food food;
    private Customer customer;

    public OrderBuilder() {
    }

    public OrderBuilder food(Food food) {
        this.food = food;
        return this;
    }

    public OrderBuilder customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public OrderDto build() {
        OrderDto order = new OrderDto();
        order.setCustomer(customer);
        order.setFood(food);
        return order;
    }
}
