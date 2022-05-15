package com.webfluxexample.api.response;

public class SummaryResponse {

    private Long totalOrders;
    private Long totalCustomers;
    private Long totalFoods;

    public SummaryResponse() {
    }

    public SummaryResponse(Long totalOrders, Long totalCustomers, Long totalFoods) {
        this.totalOrders = totalOrders;
        this.totalCustomers = totalCustomers;
        this.totalFoods = totalFoods;
    }

    public Long getTotalOrders() {
        return totalOrders;
    }

    public Long getTotalCustomers() {
        return totalCustomers;
    }

    public Long getTotalFoods() {
        return totalFoods;
    }
}
