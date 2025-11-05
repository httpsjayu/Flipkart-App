
package in.jay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import in.jay.response.ErrResponse;

@RestControllerAdvice
public class AppExpHandler {

	@ExceptionHandler(value = ReportServiceException.class)
	public ResponseEntity<ErrResponse> handleProductServiceEx(ReportServiceException pse) {

		ErrResponse resp = new ErrResponse();
		resp.getErrCode();
		resp.getMessage();

		return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
