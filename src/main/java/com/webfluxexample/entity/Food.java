package com.webfluxexample.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Food {

    @Id
    private String id;
    private String name;
    private Category category;

    public static Food of(String name, Category category) {
        return new Food(name, category);
    }

    public Food() {}

    private Food(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "{\"id\" : " + (id == null ? null : "\"" + id + "\"") +
                ",\"name\" : " + (name == null ? null : "\"" + name + "\"") +
                ",\"category\" : " + (category == null ? null : category) + "}";
    }
}
