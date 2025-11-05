package in.jay.rest;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.jay.contants.AppConstants;
import in.jay.dto.CartDto;
import in.jay.props.AppProps;
import in.jay.response.ApiResponse;
import in.jay.service.CartService;

@RestController
public class CartRestController {
	
	private final Logger log = LoggerFactory.getLogger(CartRestController.class);
	
	@Autowired
	private AppProps props;
	
	@Autowired
	private CartService service;

	@PostMapping("/cart")
	public ResponseEntity<ApiResponse<CartDto>> addToCart(@RequestBody CartDto cartDto) {

		Map<String, String> messages = props.getMessages();

		CartDto addedCart = service.addToCart(cartDto);

		ApiResponse<CartDto> response = new ApiResponse<>();

		if (addedCart != null) {
			response.setStatusCode(201);
			response.setMessage(messages.get(AppConstants.CART_ADDED));
			response.setData(cartDto);
		} else {
			log.error("Failed to add into cart");
			response.setStatusCode(500);
			response.setMessage(messages.get(AppConstants.CART_ADDED_ERR));
		}
		
		log.info("Cart added successfully");

		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

	@PutMapping("/updateCart")
	public ResponseEntity<ApiResponse<CartDto>> updateCartQuantiyById(@RequestBody CartDto cartDto) {

		Map<String, String> messages = props.getMessages();

		CartDto updatedCart = service.updateCartQuantityById(cartDto);

		ApiResponse<CartDto> response = new ApiResponse<>();

		if (updatedCart != null) {
			response.setStatusCode(201);
			response.setMessage(messages.get(AppConstants.CART_UPDATED));
			response.setData(updatedCart);
		} else {
			log.error("Failed to update cart");
			response.setStatusCode(500);
			response.setMessage(messages.get(AppConstants.CART_UPDATED_ERR));
		}
		
		log.info("Cart update successfully");

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<CartDto>> getCartByUserId(@PathVariable("id") Integer userId) {

		Map<String, String> messages = props.getMessages();

		CartDto cart = service.getCartByUserId(userId);

		ApiResponse<CartDto> response = new ApiResponse<>();

		if (cart != null) {
			response.setStatusCode(201);
			response.setMessage(messages.get(AppConstants.CART_RETRIEVE));
			response.setData(cart);
		} else {
			log.error("Failed to retrieve cart");
			response.setStatusCode(500);
			response.setMessage(messages.get(AppConstants.CART_RETRIEVE_ERR));
		}
		
		log.info("Cart retrieve successfully");

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<CartDto>> deleteCartById(@PathVariable("id") Integer cartId) {

		Map<String, String> messages = props.getMessages();

		CartDto deleteCart = service.deleteCartById(cartId);

		ApiResponse<CartDto> response = new ApiResponse<>();

		if (cartId != null) {
			response.setStatusCode(201);
			response.setMessage(messages.get(AppConstants.CART_DELETE));
			response.setData(deleteCart);
		} else {
			log.error("Failed to delete cart");
			response.setStatusCode(500);
			response.setMessage(messages.get(AppConstants.CART_DELETE_ERR));
		}
		
		log.info("Cart delete successfully");

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
