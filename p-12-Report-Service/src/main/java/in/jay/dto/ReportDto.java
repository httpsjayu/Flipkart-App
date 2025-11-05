
package in.jay.dto;

import java.time.LocalDate;

import in.jay.entity.OrderStatus;

public class ReportDto {

	private OrderStatus status;
	private LocalDate startDate;
	private LocalDate endDate;
	
	
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	
	
	
}
