package microservice.payment_service.service;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ORDER-SERVICE" , path = "/order")
public interface OrderPayment {
	
    @GetMapping("/findOrder/{id}")
	public Integer findOrder(@PathVariable Integer id);


}
