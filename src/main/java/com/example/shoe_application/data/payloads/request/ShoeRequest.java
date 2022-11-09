package com.example.shoe_application.data.payloads.request;

import com.example.shoe_application.data.models.Products;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ShoeRequest {
    @NotBlank
    @NotNull
    private String model;
    @NotBlank
    @NotNull
    private double price;
    @NotNull
    @NotBlank
    @Enumerated(EnumType.STRING)
    private Products products;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }
}
