package in.jay.mapper;

import org.modelmapper.ModelMapper;

import in.jay.dto.ProductDto;
import in.jay.entity.Product;

public class ProductMapper {
	
	public static final ModelMapper mapper = new ModelMapper();
	
	public static ProductDto convertToDto(Product product) {
		return mapper.map(product,ProductDto.class);
	}
	
	public static Product convertToEntity(ProductDto productDto) {
		return mapper.map(productDto,Product.class);
	}

}
