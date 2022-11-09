package com.example.shoe_application.data.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class NotInUse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected String brand;
    private double price;

    public NotInUse() {}

    public Long getId() {
        return id;
    }
    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotInUse notInUse = (NotInUse) o;
        return Double.compare(notInUse.price, price) == 0 && id.equals(notInUse.id) && brand.equals(notInUse.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, price);
    }

    public String toString() {
        return  "id=" + id +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }
}
