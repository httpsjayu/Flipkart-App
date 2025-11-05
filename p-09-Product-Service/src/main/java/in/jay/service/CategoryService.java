package in.jay.service;

import java.util.List;

import in.jay.dto.CategoryDto;

public interface CategoryService {
	
	public CategoryDto addCategory(CategoryDto categoryDto);
	
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	
	public List<CategoryDto> getAllCategories();
	
	public CategoryDto getCategoryById(Integer categoryId);
	
	public CategoryDto deleteCategoryById(Integer categoryId);
	

}
