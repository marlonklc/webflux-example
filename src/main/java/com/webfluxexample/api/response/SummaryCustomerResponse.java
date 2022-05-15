package com.webfluxexample.api.response;

public class SummaryCustomerResponse {

    private String foodMostOrdered;
    private int totalOders;

    public String getFoodMostOrdered() {
        return foodMostOrdered;
    }

    public void setFoodMostOrdered(String foodMostOrdered) {
        this.foodMostOrdered = foodMostOrdered;
    }

    public int getTotalOders() {
        return totalOders;
    }

    public void setTotalOders(int totalOders) {
        this.totalOders = totalOders;
    }
}
