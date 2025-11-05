
package in.jay.service;

import java.time.LocalDate;
import java.util.List;

import in.jay.dto.OrderDto;
import in.jay.entity.OrderStatus;

public interface ReportService {

	public List<OrderDto> getAllOrders();
	
	public List<OrderDto> orderByStatus(OrderStatus status);
	public List<OrderDto> getOrdersBetweenDate(LocalDate startDate , LocalDate endDate);
}
