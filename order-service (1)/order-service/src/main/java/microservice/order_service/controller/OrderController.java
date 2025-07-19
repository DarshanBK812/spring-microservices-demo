package microservice.order_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import microservice.order_service.entity.Order;
import microservice.order_service.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/add")
	public ResponseEntity<Order> addOrder(@RequestBody Order order) {

		return new ResponseEntity(orderService.addOrder(order), HttpStatus.CREATED);
	}

	@GetMapping("/findOrder/{id}")
	public Integer findOrder(@PathVariable Integer id) {
		return orderService.findOrder(id);
	}

	@GetMapping("/paid/{id}")
	public String paid(@PathVariable Integer id) {
		return orderService.paid(id);
	}

}
