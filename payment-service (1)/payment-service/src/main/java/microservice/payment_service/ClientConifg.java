package microservice.payment_service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
//
//@Configuration
//public class ClientConifg {
//
//	@Bean
//	@LoadBalanced
//	public RestTemplate restTemplet(RestTemplateBuilder builder) {
//		return new RestTemplate();
//	}
//
//}
