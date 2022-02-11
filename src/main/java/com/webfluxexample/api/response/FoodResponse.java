package com.webfluxexample.api.response;

import com.webfluxexample.entity.Category;

public class FoodResponse {

    private String id;
    private String name;
    private Category category;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "{\"id\" : " + (id == null ? null : "\"" + id + "\"") +
                ",\"name\" : " + (name == null ? null : "\"" + name + "\"") +
                ",\"category\" : " + (category == null ? null : category) + "}";
    }
}
