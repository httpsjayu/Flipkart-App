package in.jay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import in.jay.response.ErrResponse;

@RestControllerAdvice
public class AppExpHandler {
	
	@ExceptionHandler(value = ProductServiceException.class)
	public ResponseEntity<ErrResponse> handleProductEx(ProductServiceException pse){
		
		ErrResponse response = new ErrResponse();
		response.getErrorCode();
		response.getMessage();
		
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
