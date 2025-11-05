package in.jay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import in.jay.response.ErrResponse;

@RestControllerAdvice
public class CartExpHandler {
	
	public ResponseEntity<ErrResponse> handleException(CartExceptionService cse){
		
		ErrResponse response = new ErrResponse();
		response.getErrCode();
		response.getMessage();
		
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
