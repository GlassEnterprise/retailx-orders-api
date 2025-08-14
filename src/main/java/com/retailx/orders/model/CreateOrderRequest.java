package com.retailx.orders.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

/**
 * Create Order Request Model
 * 
 * Represents a request to create a new order in the RetailX system.
 * 
 * TODOs:
 * - [ ] Add support for promotional codes (RETAILX-8010)
 * - [ ] Add delivery address validation (RETAILX-8011)
 * - [ ] Add payment method validation (RETAILX-8012)
 * - [ ] Support for subscription orders (RETAILX-8013)
 */
public class CreateOrderRequest {
    
    @NotBlank(message = "Customer email is required")
    private String customerEmail;
    
    @NotNull(message = "Items are required")
    private List<OrderItem> items;
    
    private String deliveryAddress;
    
    // TODO: Add payment method field (RETAILX-8012)
    // TODO: Add promotional code field (RETAILX-8010)
    
    public CreateOrderRequest() {}
    
    public CreateOrderRequest(String customerEmail, List<OrderItem> items, String deliveryAddress) {
        this.customerEmail = customerEmail;
        this.items = items;
        this.deliveryAddress = deliveryAddress;
    }
    
    // Getters and Setters
    public String getCustomerEmail() {
        return customerEmail;
    }
    
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    
    public List<OrderItem> getItems() {
        return items;
    }
    
    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
    
    public String getDeliveryAddress() {
        return deliveryAddress;
    }
    
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
    
    /**
     * Order Item nested class
     * 
     * TODO: Move to separate file when it grows (RETAILX-8014)
     */
    public static class OrderItem {
        @NotBlank(message = "Product ID is required")
        private String productId;
        
        @Positive(message = "Quantity must be positive")
        private Integer quantity;
        
        @Positive(message = "Price must be positive")
        private BigDecimal price;
        
        public OrderItem() {}
        
        public OrderItem(String productId, Integer quantity, BigDecimal price) {
            this.productId = productId;
            this.quantity = quantity;
            this.price = price;
        }
        
        // Getters and Setters
        public String getProductId() {
            return productId;
        }
        
        public void setProductId(String productId) {
            this.productId = productId;
        }
        
        public Integer getQuantity() {
            return quantity;
        }
        
        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }
        
        public BigDecimal getPrice() {
            return price;
        }
        
        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }
}
