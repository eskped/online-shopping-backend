package com.example.shoe_application.data.models;

import javax.persistence.*;
import java.util.Objects;

@Entity // tells spring to save to db
public class Product {

    @Id // primary key in db
    @GeneratedValue(strategy = GenerationType.AUTO) // auto generate id
    protected Integer id;
    private double price;
    private String brand;
    private Integer unitsInStock;


    public Product(){}

    public Integer getId() {
        return id;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(Integer unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", units in stock=" + unitsInStock +
                '}' ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 && id.equals(product.id) && Objects.equals(brand, product.brand) && Objects.equals(unitsInStock, product.unitsInStock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, brand, unitsInStock);
    }
}
