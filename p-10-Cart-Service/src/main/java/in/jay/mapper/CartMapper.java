package in.jay.mapper;

import org.modelmapper.ModelMapper;

import in.jay.dto.CartDto;
import in.jay.entity.Cart;

public class CartMapper {
	
	public static final ModelMapper mapper = new ModelMapper();
	
	public  static CartDto convertToDto(Cart cart) {
		return mapper.map(cart, CartDto.class);
	}
	
	public  static Cart convertToEntity(CartDto cartDto) {
		return mapper.map(cartDto,Cart.class);
	}

}
