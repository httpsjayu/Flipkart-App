
package in.jay.service;

import java.time.LocalDate;
import java.util.List;

import in.jay.dto.OrderDto;

public interface OrderService {
	
	public List<OrderDto> addOrder(List<OrderDto> orderDto);
	
	public OrderDto updateOrder(OrderDto orderDto);
	
	public List<OrderDto> getOrdersByUserId(Integer userId);
	
	public List<OrderDto> getOrdersByDateAndStatus(String orderDate,
			String orderStatus);
	
	public List<OrderDto> getAllOrders();
	
}
