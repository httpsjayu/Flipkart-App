package in.jay.service;

import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import in.jay.clients.OrderClient;
import in.jay.constants.AppConstants;
import in.jay.dto.OrderDto;
import in.jay.dto.PaymentRequest;
import in.jay.props.AppProperties;
import in.jay.response.ApiResponse;

@Service
public class PaymentService {

    @Autowired
    private OrderClient client;

    @Autowired
    private AppProperties props;
   
    @Value("${razorpay.key.id}")
    private String apiKey;

    @Value("${razorpay.key.secret}")
    private String secretKey;

    public Order createOrder(PaymentRequest paymentRequest) throws RazorpayException {
		Map<String, String> keys = props.getkeys();
		RazorpayClient razorpayClient = new RazorpayClient(keys.get(AppConstants.RAZORPAY_API_KEY),
		        keys.get(AppConstants.RAZORPAY_SECRET_KEY));
		JSONObject orderRequest = new JSONObject();
		orderRequest.put(AppConstants.AMOUNT, (int) (paymentRequest.getAmount() * 100));
		orderRequest.put(AppConstants.CURRENCY, paymentRequest.getCurrency());
		orderRequest.put(AppConstants.RECEIPT, paymentRequest.getOrderId());
		orderRequest.put(AppConstants.RECEIPT, Integer.toString(paymentRequest.getOrderId()));
		return razorpayClient.orders.create(orderRequest);
	}

    public boolean verifyPaymentSignature(Map<String, Object> paymentDetails) {
        try {
            String razorpayOrderId = (String) paymentDetails.get(AppConstants.ORDER_ID);
            String razorpayPaymentId = (String) paymentDetails.get(AppConstants.PAYMENT_ID);
            String razorpaySignature = (String) paymentDetails.get(AppConstants.SIGNATURE);
            Integer orderId = (Integer) paymentDetails.get(AppConstants.RECEIPT);

            
            String payload = razorpayOrderId + "|" + razorpayPaymentId;
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
            mac.init(secretKeySpec);
            byte[] hash = mac.doFinal(payload.getBytes());
            String expectedSignature = new String(Hex.encodeHex(hash));

            if (expectedSignature.equals(razorpaySignature)) {
                OrderDto orderDto = new OrderDto();
                orderDto.setOrderId(orderId);
                orderDto.setOrderStatus("Created");

                ResponseEntity<ApiResponse<OrderDto>> updateOrder = client.updateOrder(orderDto);
                return updateOrder.getStatusCode().is2xxSuccessful();
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
