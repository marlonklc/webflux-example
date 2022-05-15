package com.webfluxexample.dto;

public class SummaryCustomerDto {

    private final String foodMostOrdered;
    private final int totalOders;

    public SummaryCustomerDto(String foodMostOrdered, int totalOders) {
        this.foodMostOrdered = foodMostOrdered;
        this.totalOders = totalOders;
    }

    public String getFoodMostOrdered() {
        return foodMostOrdered;
    }

    public int getTotalOders() {
        return totalOders;
    }
}
