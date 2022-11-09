package com.example.shoe_application.data.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer numberOfOrders;

    public Integer getId() {
        return id;
    }
    public Integer getNumberOfOrders() {
        return numberOfOrders;
    }
    public void setNumberOfOrders(Integer numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", numberOfOrders=" + numberOfOrders +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id) && numberOfOrders.equals(customer.numberOfOrders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberOfOrders);
    }
}
