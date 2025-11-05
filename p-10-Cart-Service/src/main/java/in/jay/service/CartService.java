package in.jay.service;

import in.jay.dto.CartDto;

public interface CartService {
	
	public CartDto addToCart(CartDto cartDto);
	
	public CartDto updateCartQuantityById(CartDto cartDto);
	
	public CartDto getCartByUserId(Integer userId);
	
	public CartDto deleteCartById(Integer cartId);

}
