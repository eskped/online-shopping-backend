package com.example.shoe_application.data.models;

import javax.persistence.*;
import java.util.Objects;

@Entity // tells spring to save to db
public class Shoe {
    @Id // primary key in db
    @GeneratedValue(strategy = GenerationType.AUTO) // auto generate id
    private Integer id;
    private String model;
    private double price;

    @Enumerated(EnumType.STRING) // convert enum to string
    private Products products;

    public Shoe(){}

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

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

    public void setProducts(Products products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Shoe{" +
                "id=" + id +
                " ,model='" + model + '\''+
                ", price=" + price +
                ", products=" + products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shoe shoe = (Shoe) o;
        return Double.compare(price, shoe.price) == 0 &&
                Objects.equals(id, shoe.id) &&
                Objects.equals(model, shoe.model) &&
                products == shoe.products;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, price, products);
    }
}
