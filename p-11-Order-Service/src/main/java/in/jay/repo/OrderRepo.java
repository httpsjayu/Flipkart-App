
package in.jay.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.jay.entity.Order;

public interface OrderRepo  extends JpaRepository<Order,Integer>{

	public List<Order> findByUserId(Integer userId);
	public List<Order> findByOrderDateAndOrderStatus(String orderDate,String orderStatus);
}
