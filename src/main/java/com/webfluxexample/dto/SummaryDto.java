package com.webfluxexample.dto;

public class SummaryDto {

    private Long totalOrders;
    private Long totalCustomers;
    private Long totalFoods;

    public SummaryDto(Long totalOrders, Long totalCustomers, Long totalFoods) {
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
