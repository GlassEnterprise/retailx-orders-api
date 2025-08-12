package com.retailx.orders.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Order Response Model
 * 
 * Represents an order in the RetailX system with all its details.
 * 
 * TODOs:
 * - [ ] Add tracking information (RETAILX-8015)
 * - [ ] Add estimated delivery date (RETAILX-8016)
 * - [ ] Add payment status details (RETAILX-8017)
 * - [ ] Add order history/audit trail (RETAILX-8018)
 */
public class OrderResponse {
    
    private String orderId;
    private String customerEmail;
    private List<CreateOrderRequest.OrderItem> items;
    private String deliveryAddress;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // TODO: Add tracking number field (RETAILX-8015)
    // TODO: Add estimated delivery date (RETAILX-8016)
    // TODO: Add payment status (RETAILX-8017)
    
    public OrderResponse() {}
    
    public OrderResponse(String orderId, String customerEmail, List<CreateOrderRequest.OrderItem> items,
                        String deliveryAddress, BigDecimal totalAmount, OrderStatus status,
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
    
    public OrderStatus getStatus() {
        return status;
    }
    
    public void setStatus(OrderStatus status) {
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
    
    /**
     * Order Status Enum
     * 
     * Represents the current status of an order in the fulfillment process.
     * 
     * TODOs:
     * - [ ] Add more granular statuses (RETAILX-8019)
     * - [ ] Add status transition validation (RETAILX-8020)
     */
    public enum OrderStatus {
        PENDING,        // Order created, awaiting payment
        CONFIRMED,      // Payment confirmed, processing
        SHIPPED,        // Order shipped to customer
        DELIVERED,      // Order delivered successfully
        CANCELLED       // Order cancelled
        // TODO: Add REFUNDED, RETURNED statuses (RETAILX-8019)
    }
}
