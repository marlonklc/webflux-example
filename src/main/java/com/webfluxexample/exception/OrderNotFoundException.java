package com.webfluxexample.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException() {
        super("Order not found.");
    }
}
