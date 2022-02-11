package com.webfluxexample.dto;

public class SummaryBuilder {

    private Long totalOrders;
    private Long totalCustomers;
    private Long totalFoods;

    public SummaryBuilder() {
    }

    public SummaryBuilder totalOrders(Long totalOrders) {
        this.totalOrders = totalOrders;
        return this;
    }

    public SummaryBuilder totalCustomers(Long totalCustomers) {
        this.totalCustomers = totalCustomers;
        return this;
    }

    public SummaryBuilder totalFoods(Long totalFoods) {
        this.totalFoods = totalFoods;
        return this;
    }

    public SummaryDto build() {
        return new SummaryDto(
            totalOrders,
            totalCustomers,
            totalFoods
        );
    }
}
