package microservice.payment_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

	@Id
	private int paymentId;
	private int orderId;
	private boolean paymentSuccessfull;

	public Payment() {
		super();
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public boolean isPaymentSuccessfull() {
		return paymentSuccessfull;
	}

	public void setPaymentSuccessfull(boolean paymentSuccessfull) {
		this.paymentSuccessfull = paymentSuccessfull;
	}

}
