package in.jay.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

import in.jay.dto.OrderDto;

@Component
public class PdfGenerator {
	
	public static ByteArrayInputStream generatePdf(List<OrderDto> orders) {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try {
			PdfWriter.getInstance(document ,out);
			document.open();
			
			Table table = new Table(8);
			table.setWidth(100);
			table.setPadding(3);
			
			Font headFont = new Font(Font.HELVETICA,12,Font.BOLD);
			
			table.addCell(new Phrase ("Order ID",headFont));
			table.addCell(new Phrase ("Order Date",headFont));
			table.addCell(new Phrase ("Payment Type",headFont));
			table.addCell(new Phrase ("Price" ,headFont));
			table.addCell(new Phrase ("Product ID",headFont));
			table.addCell(new Phrase ("Quantity",headFont));
			table.addCell(new Phrase ("Status",headFont));
			table.addCell(new Phrase ("User ID",headFont));
			
			for (OrderDto order : orders) {
				table.addCell(String.valueOf(order.getOrderId()));
				table.addCell(String.valueOf(order.getOrderDate()));
				table.addCell(String.valueOf(order.getPaymentType()));
				table.addCell(String.valueOf(order.getPrice()));
				table.addCell(String.valueOf(order.getProductId()));
				table.addCell(String.valueOf(order.getQuantity()));
				table.addCell(String.valueOf(order.getStatus()));
				table.addCell(String.valueOf(order.getUserId()));
			}
			
			document.add(table);
			document.close();
			
			
		}
		catch(DocumentException ex) {
			ex.printStackTrace();
		}
		
		return new ByteArrayInputStream(out.toByteArray());
		
	}

}
