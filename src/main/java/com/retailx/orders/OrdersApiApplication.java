package com.retailx.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * RetailX Orders API Application
 * 
 * Main entry point for the RetailX Orders management service.
 * Handles order creation, tracking, and integrates with notification services.
 * 
 * INTEGRATION DEPENDENCIES:
 * - foo-legacy-notifications-api (port 8081) - For order status notifications
 * 
 * TODOs:
 * - [ ] Migrate to new RetailX Messaging Hub (RETAILX-8001)
 * - [ ] Add service discovery integration (RETAILX-8002)
 * - [ ] Implement database persistence (RETAILX-8003)
 * - [ ] Add Redis caching layer (RETAILX-8004)
 * - [ ] Integrate with message queue for order events (RETAILX-8005)
 * 
 * @author RetailX Platform Team
 * @version 2.1.3
 */
@SpringBootApplication
public class OrdersApiApplication {

    public static void main(String[] args) {
        // TODO: Add startup health check for notification service (RETAILX-8006)
        SpringApplication.run(OrdersApiApplication.class, args);
    }
}
