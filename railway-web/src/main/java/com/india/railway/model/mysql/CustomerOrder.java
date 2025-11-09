package com.india.railway.model.mysql;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer_Mysql customer;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems;

    private LocalDateTime orderDate;

    // Default constructor
    public CustomerOrder() {
    }

    public CustomerOrder(Customer_Mysql customer, List<OrderItem> orderItems) {
        this.customer = customer;
        this.orderItems = orderItems;
        this.orderDate = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer_Mysql getCustomer() {
        return customer;
    }

    public void setCustomer(Customer_Mysql customer) {
        this.customer = customer;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
