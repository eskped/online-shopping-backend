package com.example.shoe_application.data.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;
    private double totalPrice;

    // maps product id to number of product sold
    @Type( type = "json" )
    private HashMap<Product, Integer> products;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;

    public Sale() {
    }



    public Sale(HashMap<Product, Integer> products) {
        this.products = products;
    }

    public Integer getId() {
        return id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }


    public void removeProduct(Product product, Integer count) {
        if (products.containsKey(product)) {
            products.put(product, products.get(product) - count);
        } else {
            products.put(product, count);
        }
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

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime date) {
        this.dateCreated = date;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
}
