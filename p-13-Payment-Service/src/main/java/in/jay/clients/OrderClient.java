package in.jay.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import in.jay.dto.OrderDto;
import in.jay.response.ApiResponse;

@FeignClient(name = "p-11-Order-Service")
public interface OrderClient {

	@PutMapping("/updateOrder")
	public ResponseEntity<ApiResponse<OrderDto>> updateOrder(@RequestBody OrderDto orderDto);
		
	
	
}
