package com.example.shoe_application.data.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "Customer")
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    private boolean giftcard;
    @OneToMany(cascade = { CascadeType.ALL})
    @JoinColumn(name = "customer_sale")
    private Set<Sale> salesDone = new HashSet<>();

    public Customer() {
        this.giftcard = false;
    }

    public Integer getId() {
        return id;
    }

    public void addSale(Sale sale) {
        // assumes sales only have relationship to customer thorugh app
        if (this.salesDone.size() % 3 == 0) {
            sale.setTotalPrice(sale.getTotalPrice() - 750);
        }
        salesDone.add(sale);
        sale.setCustomer(this);
    }

    public void removeSale(Sale sale) {
        salesDone.remove(sale);
        sale.setCustomer(null);
    }

    public Integer getNumberOfOrders() {
        return salesDone.size();
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                '}';
    }

}
