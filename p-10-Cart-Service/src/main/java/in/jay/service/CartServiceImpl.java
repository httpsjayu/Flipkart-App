package in.jay.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.jay.dto.CartDto;
import in.jay.entity.Cart;
import in.jay.mapper.CartMapper;
import in.jay.repo.CartRepo;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepo repo;

	@Override
	public CartDto addToCart(CartDto cartDto) {

		Cart cart = CartMapper.convertToEntity(cartDto);

		Cart savedCart = repo.save(cart);

		return CartMapper.convertToDto(savedCart);
	}

	@Override
	public CartDto updateCartQuantityById(CartDto cartDto) {
		
		Optional<Cart> cartById = repo.findById(cartDto.getCartId());
		
		if(cartById.isPresent()) {
			Cart cart = cartById.get();
			cart.setProductId(cartDto.getProductId());
			cart.setQuantity(cartDto.getQuantity());
			Cart updatedCart = repo.save(cart);
			return CartMapper.convertToDto(updatedCart);
		}
		
		else {
			throw new NoSuchElementException("Cart not found for ID :" + cartDto.getCartId());
		}
	}

	@Override
	public CartDto getCartByUserId(Integer userId) {
	
		Cart cart = repo.findById(userId).orElseThrow();
		
		return CartMapper.convertToDto(cart);
	}

	@Override
	public CartDto deleteCartById(Integer cartId) {
		
		Cart cart = repo.findById(cartId).orElseThrow();
		
		CartDto dto = CartMapper.convertToDto(cart);
		
		repo.deleteById(cartId);
		
		return dto;
	
		
	}

}
