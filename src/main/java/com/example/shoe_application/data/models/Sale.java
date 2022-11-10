package com.example.shoe_application.data.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity(name = "Sale")
@Table(name = "sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;
    private double totalPrice;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
    @ManyToMany(fetch = FetchType.LAZY,
    cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "sale")
    private Set<Product> products = new HashSet<>();

    public Sale() {}

    public Integer getId() {
        return id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public double getTotalPrice() {
        return totalPrice;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void removeProduct(Integer productId) {
        Optional<Product> product = this.products.stream()
                .filter((p) -> Objects.equals(p.getId(), productId)).findFirst();
        if (product.isPresent()) {
            this.products.remove(product.get());
            product.get().getSales().remove(this);
        }
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
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
