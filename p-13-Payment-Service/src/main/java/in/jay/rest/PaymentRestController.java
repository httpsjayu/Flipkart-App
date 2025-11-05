package in.jay.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.Order;
import com.razorpay.RazorpayException;

import in.jay.constants.AppConstants;
import in.jay.dto.OrderResponse;
import in.jay.dto.PaymentRequest;
import in.jay.mapper.OrderMapper;
import in.jay.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentRestController {
	
	@Autowired
	private PaymentService service;
	
	@PostMapping(value = "/intiate-payment",produces = "application/json")
	public ResponseEntity<OrderResponse> createOrder(@RequestBody PaymentRequest paymentRequest) throws RazorpayException{
		
		Order order = service.createOrder(paymentRequest);
		
		OrderResponse convertToOrderResponse = OrderMapper.convertToOrderResponse(order);
		
		if(convertToOrderResponse != null) {
			return new ResponseEntity<>(convertToOrderResponse,HttpStatus.OK);
		}
		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
				
	}
	
	@PostMapping("/payment-callback")
	public ResponseEntity<String> handleCallBack(Map<String,Object> payload){
		boolean verifyPaymentSignature = service.verifyPaymentSignature(payload);
		
		if(verifyPaymentSignature) {
			return new ResponseEntity<>(AppConstants.SUCCESS_MSG,HttpStatus.OK);
		}
		else {
		return new ResponseEntity<>(AppConstants.FAILED_MSG,HttpStatus.BAD_REQUEST);
		}
	}

}
