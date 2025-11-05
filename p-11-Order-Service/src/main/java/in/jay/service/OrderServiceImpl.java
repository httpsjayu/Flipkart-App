
package in.jay.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.jay.dto.OrderDto;
import in.jay.entity.Order;
import in.jay.mapper.OrderMapper;
import in.jay.repo.OrderRepo;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepo orderRepo;

	@Override
	public List<OrderDto> addOrder(List<OrderDto> orderDto) {
		  List<Order> orderEntities = orderDto.stream()
                  .map(OrderMapper::convertToEntity)
                  .collect(Collectors.toList());
		List<Order> orders=orderRepo.saveAll(orderEntities);
		 return orders.stream().map(OrderMapper::convertToDto).collect(Collectors.toList());
	}

	@Override
	public OrderDto updateOrder(OrderDto orderDto) {
		Order orderEntity=OrderMapper.convertToEntity(orderDto);
		Order order=orderRepo.findById(orderEntity.getOrderId()).orElseThrow();
		if(order!=null) {
			order.setProductId(orderDto.getProductId());
			order.setPrice(orderDto.getPrice());
			order.setQuantity(orderDto.getQuantity());
			order.setPaymentType(orderDto.getPaymentType());
			order.setOrderDate(orderDto.getOrderDate());
			order.setOrderStatus(orderDto.getOrderStatus());
			Order updatedOrder=orderRepo.save(order);
			return OrderMapper.convertToDto(updatedOrder);
			
		}else {
		return null;
		}
	}

	@Override
	public List<OrderDto> getOrdersByUserId(Integer userId) {

		 List<Order> orders = orderRepo.findByUserId(userId);
		    if (orders != null && !orders.isEmpty()) {
		        return orders.stream().map(OrderMapper::convertToDto).collect(Collectors.toList());
		    } else {
		        return null;
		    }
	}

	@Override
	public List<OrderDto> getOrdersByDateAndStatus(String orderDate, String orderStatus) {
		List<Order> orders=orderRepo.findByOrderDateAndOrderStatus(orderDate, orderStatus);
		if(orders!=null) {
			return orders.stream().map(OrderMapper::convertToDto).collect(Collectors.toList());
			}
			else {
				return null;
			}
	}

	@Override
	public List<OrderDto> getAllOrders() {
		List<Order> orders=orderRepo.findAll();
		return orders.stream().map(OrderMapper::convertToDto).collect(Collectors.toList());
	}
}
