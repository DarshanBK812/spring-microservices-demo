package microservice.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PAYMENT-SERVICE", path = "/payment")
public interface OrderPayment {

	@GetMapping("/checkPay/{orderId}")
	public boolean isPaymentSuccessful(@PathVariable Integer orderId);
}
