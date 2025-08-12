# RetailX Orders API

## Overview

The **retailx-orders-api** is a core service in the RetailX platform responsible for order management and processing. This service handles order creation, tracking, and integrates with the notification system to keep customers informed about their order status.

## Version

**Current Version:** 2.1.3

## Technology Stack

- **Java 17**
- **Spring Boot 3.2.0**
- **Maven 3.9.5** (via wrapper)
- **OpenAPI 3** (Swagger UI)
- **Docker** support included

## Service Dependencies

### External Service Integrations
- **foo-legacy-notifications-api** (port 8081) - For customer notifications
  - Used for order confirmation emails
  - Used for order status update notifications

### Integration Architecture
```
retailx-orders-api (8082)
    ↓ HTTP calls (simulated)
foo-legacy-notifications-api (8081)
    ↓ Email/SMS/Push
Customer Notifications
```

## API Endpoints

### Base URL
```
http://localhost:8082
```

### Implemented Endpoints

#### 1. Create Order
```http
POST /orders
Content-Type: application/json

{
  "customerEmail": "customer@example.com",
  "items": [
    {
      "productId": "PROD-123",
      "quantity": 2,
      "price": 29.99
    }
  ],
  "deliveryAddress": "123 Main St, City, State 12345"
}
```

**Response (201 Created):**
```json
{
  "orderId": "ORD-A1B2C3D4",
  "customerEmail": "customer@example.com",
  "items": [
    {
      "productId": "PROD-123",
      "quantity": 2,
      "price": 29.99
    }
  ],
  "deliveryAddress": "123 Main St, City, State 12345",
  "totalAmount": 59.98,
  "status": "PENDING",
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-15T10:30:00"
}
```

**Integration Behavior:**
- Automatically triggers notification via `foo-legacy-notifications-api`
- Sends order confirmation email to customer
- Logs mock HTTP request/response for demonstration

#### 2. Get Order Details
```http
GET /orders/{orderId}
```

**Response (200 OK):**
```json
{
  "orderId": "ORD-A1B2C3D4",
  "customerEmail": "customer@example.com",
  "items": [
    {
      "productId": "PROD-123",
      "quantity": 2,
      "price": 29.99
    }
  ],
  "deliveryAddress": "123 Main St, City, State 12345",
  "totalAmount": 59.98,
  "status": "PENDING",
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-15T10:30:00"
}
```

### Order Status Values
- `PENDING` - Order created, awaiting payment
- `CONFIRMED` - Payment confirmed, processing
- `SHIPPED` - Order shipped to customer
- `DELIVERED` - Order delivered successfully
- `CANCELLED` - Order cancelled

## Running Locally

### Prerequisites
- Java 17 or higher
- No Maven installation required (uses wrapper)

### Quick Start

1. **Navigate to the project:**
   ```bash
   cd retailx-orders-api
   ```

2. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```

3. **The service will start on port 8082:**
   ```
   http://localhost:8082
   ```

4. **Access OpenAPI documentation:**
   ```
   http://localhost:8082/swagger-ui.html
   ```

5. **Test the API:**
   ```bash
   # Create an order
   curl -X POST http://localhost:8082/orders \
     -H "Content-Type: application/json" \
     -d '{
       "customerEmail": "test@example.com",
       "items": [
         {
           "productId": "PROD-123",
           "quantity": 1,
           "price": 49.99
         }
       ],
       "deliveryAddress": "123 Test St, Test City, TC 12345"
     }'

   # Get order details (replace {orderId} with actual ID from response)
   curl http://localhost:8082/orders/{orderId}
   ```

### Running with Dependencies

To test the full integration, run both services:

```bash
# Terminal 1: Start notification service
cd foo-legacy-notifications-api
./mvnw spring-boot:run

# Terminal 2: Start orders service
cd retailx-orders-api
./mvnw spring-boot:run
```

### Building

```bash
# Build the project
./mvnw clean package

# Run tests
./mvnw test

# Build Docker image
docker build -t retailx-orders-api .

# Run with Docker
docker run -p 8082:8082 retailx-orders-api
```

## Dependencies

### Runtime Dependencies
- `spring-boot-starter-web` - REST API framework
- `spring-boot-starter-validation` - Request validation
- `spring-boot-starter-webflux` - HTTP client for service integration
- `springdoc-openapi-starter-webmvc-ui` - OpenAPI 3 documentation

### Development Dependencies
- `spring-boot-starter-test` - Testing framework

## Project Structure

```
retailx-orders-api/
├── src/main/java/com/retailx/orders/
│   ├── OrdersApiApplication.java              # Main application class
│   ├── controller/
│   │   └── OrderController.java               # REST endpoints
│   ├── model/
│   │   ├── CreateOrderRequest.java            # Request model
│   │   └── OrderResponse.java                 # Response model
│   ├── service/
│   │   └── OrderService.java                  # Business logic
│   └── client/
│       └── NotificationClient.java            # Integration client
├── src/main/resources/
│   └── application.properties                 # Configuration
├── Dockerfile                                 # Container configuration
├── pom.xml                                    # Maven configuration
└── README.md                                  # This file
```

## Service Integration Details

### Notification Service Integration

The orders API integrates with `foo-legacy-notifications-api` through the `NotificationClient` class:

**Mock Implementation:**
- Simulates HTTP POST requests to `/v1/notifications`
- Logs request/response payloads for demonstration
- No actual network calls are made (for demo purposes)

**Integration Points:**
1. **Order Creation** → Sends confirmation notification
2. **Status Updates** → Sends status change notifications

**Configuration:**
```properties
retailx.services.notifications.base-url=http://localhost:8081
retailx.services.notifications.timeout=5000
```

## Current Limitations

### Mock Implementation
- **In-Memory Storage**: Orders are stored in memory (lost on restart)
- **Simulated HTTP Calls**: No actual network requests to notification service
- **No Payment Processing**: Orders are created without payment validation
- **No Inventory Validation**: No stock checking before order creation

### Missing Features
- **Database Persistence**: No permanent storage
- **Authentication**: No customer authorization checks
- **Rate Limiting**: No request throttling
- **Caching**: No Redis or similar caching layer
- **Message Queues**: No event publishing for order lifecycle

## TODOs & Migration Plans

### High Priority (Core Functionality)
- [ ] **RETAILX-8003**: Implement database persistence (PostgreSQL/MySQL)
- [ ] **RETAILX-8030**: Add inventory validation before order creation
- [ ] **RETAILX-8031**: Integrate payment processing service
- [ ] **RETAILX-8021**: Implement actual HTTP client calls to notification service

### Medium Priority (Integration & Reliability)
- [ ] **RETAILX-8001**: Migrate to new RetailX Messaging Hub
- [ ] **RETAILX-8002**: Add service discovery integration
- [ ] **RETAILX-8022**: Add retry mechanism with exponential backoff
- [ ] **RETAILX-8023**: Add circuit breaker pattern for resilience
- [ ] **RETAILX-8005**: Integrate with message queue for order events

### Low Priority (Enhancements)
- [ ] **RETAILX-8032**: Add order cancellation functionality
- [ ] **RETAILX-8035**: Add endpoint to list orders by customer
- [ ] **RETAILX-8036**: Add order search and filtering
- [ ] **RETAILX-8037**: Add order status update endpoint
- [ ] **RETAILX-8038**: Add pagination for order listing

### Security & Operations
- [ ] **RETAILX-8039**: Add rate limiting
- [ ] **RETAILX-8043**: Add customer authorization checks
- [ ] **RETAILX-8040**: Enhanced request/response validation
- [ ] **RETAILX-8041**: Add idempotency support
- [ ] **RETAILX-8042**: Improve error handling and status codes

### DevOps & Monitoring
- [ ] **RETAILX-8045**: Add health check endpoints
- [ ] **RETAILX-8050**: Use multi-stage Docker build
- [ ] **RETAILX-8051**: Add non-root user for Docker security
- [ ] **RETAILX-8052**: Implement proper health checks

## API Documentation

When running locally, comprehensive API documentation is available at:
- **Swagger UI**: http://localhost:8082/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8082/api-docs

## Contact

For questions or integration support, contact the **RetailX Platform Team**.

## License

Internal RetailX Platform Service - Not for external distribution.

---

**🔗 Service Dependencies**: This service requires `foo-legacy-notifications-api` to be running on port 8081 for full functionality.
