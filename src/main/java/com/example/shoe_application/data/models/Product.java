package com.example.shoe_application.data.models;

import com.example.shoe_application.exception.ResourceNotFoundException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity // tells spring to save to db
@Table(name = "product")
public class Product {

    @Id // primary key in db
    @GeneratedValue(strategy = GenerationType.AUTO) // auto generate id
    protected Integer id;
    private double price;
    private String brand;
    private Integer unitsInStock;

    // relationship to sale
    @ManyToMany(fetch = FetchType.LAZY,
        cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "product_sale",
            joinColumns = { @JoinColumn(name = "product_id") },
            inverseJoinColumns = { @JoinColumn(name = "sale_id") })
    @JsonIgnore
    private Set<Sale> sales = new HashSet<>();


    public Product(){}

    public Integer getId() {
        return id;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void addToSale(Sale sale) {
        this.sales.add(sale);
        sale.getProducts().add(this);
    }

    public void removeSale(Integer saleId) throws ResourceNotFoundException {
        Sale sale = this.sales.stream()
                .filter(s -> Objects.equals(s.getId(), saleId)).findFirst()
                .orElseThrow();

        this.sales.remove(sale);
        sale.getProducts().remove(this);

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
