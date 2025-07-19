package microservice.payment_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import microservice.payment_service.entity.Payment;
import microservice.payment_service.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PayementController {

	@Autowired
	private PaymentService service;

	@PostMapping("/pay")
	public ResponseEntity<Boolean> pay(@RequestBody Payment payment) {
		return new ResponseEntity(service.pay(payment), HttpStatus.OK);
	}

	 @GetMapping("/checkPay/{orderId}")
	  public boolean isPaymentSuccessful(@PathVariable Integer orderId) {
	    return service.isPaymentSuccessful(orderId);
	  }
}
