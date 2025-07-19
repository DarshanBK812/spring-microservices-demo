package microservice.payment_service.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import microservice.payment_service.entity.Payment;
import microservice.payment_service.repository.PaymentRepo;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepo repo;

//	@Autowired
//	private RestTemplate restTemplate;

	@Autowired
	private OrderPayment orderPayment;

	private final Logger logger = LoggerFactory.getLogger(PaymentService.class);

//	 this is the hardcoded url we communucate with other service not a good
//			 practice
//	public Payment pay(Payment payment) {
//		int id = payment.getOrderId();
//
//		
//		try {
//			int orderId = restTemplate.getForObject("http://ORDER-SERVICE/order/findOrder/" + id, Integer.class);
//		} catch (Exception e) {
//			throw new RuntimeException("Order id problem :" + e.getMessage());
//		}
//		return repo.save(payment);
//	}
//<--------------------------------------------------------------------------------------------------------------------------------------------------->

	public Payment pay(Payment payment) {
		int id = payment.getOrderId();

		logger.error("Payment fetched succesfull " + id);

		try {
			Integer orderId = orderPayment.findOrder(id);
		} catch (Exception e) {
			throw new RuntimeException("Order id problem :" + e.getMessage());
		}
		return repo.save(payment);
	}

	public boolean isPaymentSuccessful(Integer orderId) {
		return repo.findByOrderId(orderId).map(Payment::isPaymentSuccessfull).orElse(false);
	}

}
