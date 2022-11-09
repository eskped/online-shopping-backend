package com.example.shoe_application.data.payloads.request;

import com.example.shoe_application.data.models.Product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class SaleRequest {
    @NotBlank
    @NotNull
    private double totalPrice;
    @NotBlank
    @NotNull
    private HashMap<Product, Integer> products;

    @NotBlank
    @NotNull
    private LocalDateTime dateCreated;

    private List<Integer> productIds;
    private List<Integer> productQuantity;

    public double getTotalPrice() {return totalPrice; }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public HashMap<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(HashMap<Product, Integer> products) {
        this.products = products;
    }


}
