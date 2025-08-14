package com.retailx.orders.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    private String orderId;
    private String customerEmail;
    @ElementCollection
    private List<CreateOrderRequest.OrderItem> items;
    private String deliveryAddress;
    private BigDecimal totalAmount;
    private OrderResponse.OrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Order() {}

    public Order(String orderId, String customerEmail, List<CreateOrderRequest.OrderItem> items,
                 String deliveryAddress, BigDecimal totalAmount, OrderResponse.OrderStatus status,
                 LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.orderId = orderId;
        this.customerEmail = customerEmail;
        this.items = items;
        this.deliveryAddress = deliveryAddress;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public List<CreateOrderRequest.OrderItem> getItems() {
        return items;
    }

    public void setItems(List<CreateOrderRequest.OrderItem> items) {
        this.items = items;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderResponse.OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderResponse.OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
