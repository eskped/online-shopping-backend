package com.example.shoe_application.data.payloads.request;

import com.example.shoe_application.data.models.Sale;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class ProductRequest {

    @NotBlank
    @NotBlank
    private Integer id;
    @NotBlank
    @NotNull
    private String brand;
    @NotBlank
    @NotNull
    private double price;
    @NotNull
    @NotBlank
    private Integer unitsInStock;
    private Set<Sale> sales;

    public String getBrand() {
        return brand;
    }

    public Integer getId() {return id;}

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(Integer unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public Set<Sale> getSales() {return sales;}
}
