package microservice.order_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import microservice.order_service.client.OrderPayment;
import microservice.order_service.entity.Order;
import microservice.order_service.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private OrderPayment orderPayment;

	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

	public Order addOrder(Order order) {

		return repository.save(order);
	}

	public Integer findOrder(Integer id) {
		repository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
		return id;
	}

	@CircuitBreaker(name = "paymentService", fallbackMethod = "onPaymentDown")
	public String paid(Integer id) {
		if (orderPayment.isPaymentSuccessful(id)) {
			return "Order booked successfully";
		} else {
			return "Order booking failed";
		}
	}

	public String onPaymentDown(Integer id, Throwable throwable) {
		logger.error("Payment service call failed for order " + id, throwable);
		return "Payment service is temporarily unavailable. Please try again later.";
	}

}
