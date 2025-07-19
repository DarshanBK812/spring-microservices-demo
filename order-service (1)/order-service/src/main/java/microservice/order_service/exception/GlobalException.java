package microservice.order_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> exception(Exception exception) {
		return new ResponseEntity("Somethingwent wrong : " + exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
