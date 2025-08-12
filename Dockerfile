# RetailX Orders API Dockerfile
# 
# Multi-service architecture component that integrates with:
# - foo-legacy-notifications-api (port 8081)
# 
# TODOs:
# - [ ] Use multi-stage build for smaller image size (RETAILX-8050)
# - [ ] Add non-root user for security (RETAILX-8051)
# - [ ] Implement proper health checks (RETAILX-8052)
# - [ ] Add build-time security scanning (RETAILX-8053)
# - [ ] Add service discovery integration (RETAILX-8002)

FROM openjdk:17-jdk-slim

# TODO: Create non-root user for security (RETAILX-8051)
WORKDIR /app

# Copy Maven wrapper and pom.xml for dependency caching
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# TODO: Use multi-stage build to avoid including Maven in final image (RETAILX-8050)
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# TODO: Add proper health check endpoint and configure here (RETAILX-8052)
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8082/actuator/health || exit 1

# Expose port 8082 (orders API port)
EXPOSE 8082

# Environment variables for service integration
# TODO: Move to service discovery (RETAILX-8002)
ENV RETAILX_SERVICES_NOTIFICATIONS_BASE_URL=http://foo-legacy-notifications-api:8081
ENV RETAILX_SERVICES_NOTIFICATIONS_TIMEOUT=5000

# TODO: Run as non-root user (RETAILX-8051)
CMD ["java", "-jar", "target/retailx-orders-api-2.1.3.jar"]
