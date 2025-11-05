
package in.jay.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.jay.repo.OrderRepo;
import in.jay.dto.OrderDto;
import in.jay.entity.Order;
import in.jay.entity.OrderStatus;
import in.jay.mapper.OrderMapper;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private OrderRepo orderRepo;

	@Override
	public List<OrderDto> getAllOrders() {

		List<Order> orders = orderRepo.findAll();

		return orders.stream().map(OrderMapper::convertToDto).collect(Collectors.toList());
	}

	@Override
	public List<OrderDto> orderByStatus(OrderStatus status) {
		List<Order> byStatus = orderRepo.findbyStatus(status);
		return byStatus.stream().map(OrderMapper::convertToDto).collect(Collectors.toList());
	}

	@Override
	public List<OrderDto> getOrdersBetweenDate(LocalDate startDate, LocalDate endDate) {
		List<Order> dates = orderRepo.findOrderBetweenDates(startDate, endDate);
		return dates.stream().map(OrderMapper::convertToDto).collect(Collectors.toList());
	}

}
