package in.jay.mapper;

import org.modelmapper.ModelMapper;

import in.jay.dto.CategoryDto;
import in.jay.entity.Category;

public class CategoryMapper {
	
	public static final ModelMapper mapper = new ModelMapper();
	
	public static CategoryDto convertToDto(Category category) {
		return mapper.map(category,CategoryDto.class);
	}
	
	public static Category convertToEntity(CategoryDto categoryDto) {
		return mapper.map(categoryDto,Category.class);
	}

}
