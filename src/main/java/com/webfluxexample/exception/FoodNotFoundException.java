package com.webfluxexample.exception;

public class FoodNotFoundException extends RuntimeException {

    public FoodNotFoundException() {
        super("Food not found.");
    }
}
