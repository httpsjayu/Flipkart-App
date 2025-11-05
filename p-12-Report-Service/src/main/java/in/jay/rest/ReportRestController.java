
package in.jay.rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.jay.dto.OrderDto;
import in.jay.dto.ReportDto;
import in.jay.exception.ReportServiceException;
import in.jay.service.ReportService;
import in.jay.utils.ExcelGenerator;
import in.jay.utils.PdfGenerator;

@RestController
@RequestMapping("/reports")
public class ReportRestController {

	@Autowired
	private ReportService reportsService;

	@GetMapping("/FilterOrderExcel")
	public ResponseEntity<InputStreamResource> downloadExcel(ReportDto reportDto) throws ReportServiceException {
		List<OrderDto> orders = new ArrayList<>();

		if (reportDto.getStatus() != null) {
			orders = reportsService.orderByStatus(reportDto.getStatus());
		}

		if (reportDto.getStartDate() != null || reportDto.getEndDate() != null) {
			List<OrderDto> dateFilteredOrders = reportsService.getOrdersBetweenDate(reportDto.getStartDate(),
					reportDto.getEndDate());
			if (!orders.isEmpty()) {
				orders.retainAll(dateFilteredOrders);
			} else {
				orders = dateFilteredOrders;
			}
		}

		if (reportDto.getStatus() == null && reportDto.getStartDate() == null && reportDto.getEndDate() == null) {
			orders = reportsService.getAllOrders();
		}

		if (orders != null) {
			try {
				ByteArrayInputStream in = ExcelGenerator.generateExcel(orders);
				InputStreamResource resource = new InputStreamResource(in);
				HttpHeaders headers = new HttpHeaders();
				return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_OCTET_STREAM)
						.body(resource);
			} catch (IOException e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	@GetMapping("/FilterOrderPdf")
	public ResponseEntity<InputStreamResource> downloadPdf(ReportDto reportDto) throws ReportServiceException {

		List<OrderDto> orders = new ArrayList<>();

		if (reportDto.getStatus() != null) {
			orders = reportsService.orderByStatus(reportDto.getStatus());
		}

		if (reportDto.getStartDate() != null || reportDto.getEndDate() != null) {
			List<OrderDto> dateFilteredOrders = reportsService.getOrdersBetweenDate(reportDto.getStartDate(),
					reportDto.getEndDate());
			if (!orders.isEmpty()) {
				orders.retainAll(dateFilteredOrders);
			} else {
				orders = dateFilteredOrders;
			}
		}

		if (reportDto.getStatus() == null && reportDto.getStartDate() == null && reportDto.getEndDate() == null) {
			orders = reportsService.getAllOrders();
		}

		if (orders != null) {
			ByteArrayInputStream in = PdfGenerator.generatePdf(orders);
			InputStreamResource resource = new InputStreamResource(in);
			HttpHeaders headers = new HttpHeaders();
			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

}
