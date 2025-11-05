
package in.jay.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.jay.entity.Order;
import in.jay.entity.OrderStatus;

public interface OrderRepo extends JpaRepository<Order, Integer> {
	
	@Query("Select o From Order o Where o.status= :status")
	List<Order> findbyStatus(OrderStatus status);

	@Query("Select o from Order o Where o.orderDate Between :startDate And :endDate")
	List<Order> findOrderBetweenDates(@Param("startDate")LocalDate startDate , @Param("endDate")LocalDate endDate);

}
