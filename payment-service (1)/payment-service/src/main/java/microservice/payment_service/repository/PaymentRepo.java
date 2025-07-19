package microservice.payment_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import microservice.payment_service.entity.Payment;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer> {

	  Optional<Payment> findByOrderId(Integer orderId);

}
