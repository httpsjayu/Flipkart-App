package in.jay.mapper;

import com.razorpay.Order;

import in.jay.dto.OrderResponse;

public class OrderMapper {
	
	public static OrderResponse convertToOrderResponse(Order order) {
		
		OrderResponse response = new OrderResponse();
		response.setId(order.get("id"));
		response.setAmount(order.get("amount"));
		response.setCurrency(order.get("currency"));
		response.setStatus(order.get("status"));
		response.setReceipt(order.get("receipt"));
		return response;
		
	}

}
