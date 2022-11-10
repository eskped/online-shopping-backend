package com.example.shoe_application.data.payloads.request;

import com.example.shoe_application.data.models.Product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Set;

public class SaleRequest {

    @NotBlank
    @NotNull
    private Integer id;
    @NotBlank
    @NotNull
    private double totalPrice;
    @NotBlank
    @NotNull
    private LocalDateTime dateCreated;

    private LocalDateTime dateUpdated;

    private Set <Product> products;

    public Integer getId() {return id;}
    public double getTotalPrice() {return totalPrice; }

    public void setTotalPrice(double totalPrice) {this.totalPrice = totalPrice;}

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public LocalDateTime getDatedateUpdated() {
        return dateUpdated;
    }
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {this.products = products;}

}
