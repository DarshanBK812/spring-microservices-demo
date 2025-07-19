# Spring Microservices Demo

## Overview
A Java Spring Boot microservices ecosystem demonstrating:
- **API Gateway** (Spring Cloud Gateway)  
- **Config Server** (Spring Cloud Config)  
- **Service Registry** (Eureka)  
- **Order Service**  
- **Payment Service**  

Each service:
- Loads centralized configuration from a Git‑backed Config Server(check my repo :- "config-repo")  
- Registers with Eureka for discovery  
- Communicates via Feign through the Gateway  

---

## Prerequisites
- **Java 17+ JDK**  
- **Maven 3.6+**  
- **Git**  
- **MySQL** server on `localhost:3306`  
- **Docker** (to run Zipkin for distributed tracing)

---

## Before You Start
1. **Run Zipkin in Docker**:
   ```bash
   docker run -d -p 9411:9411 openzipkin/zipkin
Zipkin UI will be available at http://localhost:9411.

Service Access Credentials
When you send a request to the gateway (e.g. in your browser) at:

bash
Copy
Edit
http://localhost:8080/order-service/order/paid/8
your browser will prompt for HTTP Basic Authentication. Enter:

Username: user

Password: user

Startup Sequence
Start each component in its own terminal, in this order:

Service Registry (Eureka)

bash
Copy
Edit
cd service-registry
mvn spring-boot:run
Config Server

bash
Copy
Edit
cd ../config-server
mvn spring-boot:run
API Gateway

bash
Copy
Edit
cd ../api-gateway
mvn spring-boot:run
Order Service

bash
Copy
Edit
cd ../order-service
mvn spring-boot:run
Payment Service

bash
Copy
Edit
cd ../payment-service
mvn spring-boot:run
Verifying Services
Eureka UI: http://localhost:8761 (all services should appear)

Gateway Routes: http://localhost:8080/actuator/gateway/routes

Health Endpoints:

Gateway: http://localhost:8080/actuator/health

Order: http://localhost:8081/actuator/health

Payment: http://localhost:8082/actuator/health

Request Flow
Client calls:

sql
Copy
Edit
GET http://localhost:8080/order-service/order/paid/8
Gateway

Matches Path=/order-service/**

Strips /order-service prefix

Discovers order-service via Eureka (port 8081)

Forwards to

bash
Copy
Edit
GET http://localhost:8081/order/paid/8
Order Service

Receives /order/paid/8

Invokes Payment Service via Feign:

java
Copy
Edit
@FeignClient(name = "payment-service", path = "/payment")
public interface PaymentClient {
  @GetMapping("/paid/{orderId}")
  PaymentResponse markPaid(@PathVariable Long orderId);
}
Payment Service

Updates payment status

Returns JSON:

json
Copy
Edit
{ "orderId": 8, "status": "PAID" }
Order Service wraps that response and returns to the Gateway, which relays it back to the client.

Testing Endpoints
Via Gateway

bash
Copy
Edit
curl -u user:user http://localhost:8080/order-service/order/paid/8
Direct Service Calls

bash
Copy
Edit
curl -u user:user http://localhost:8081/order/paid/8
curl -u user:user http://localhost:8082/payment/paid/8


© 2025 Darshan B K
