package in.jay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.jay.contants.AppConstants;
import in.jay.dto.CategoryDto;
import in.jay.entity.Category;
import in.jay.exception.ProductServiceException;
import in.jay.mapper.CategoryMapper;
import in.jay.repo.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepo categoryRepo;
	
	
	
	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {
		
		Category category = CategoryMapper.convertToEntity(categoryDto);
		
		Category savedCategory = categoryRepo.save(category);
		  
		    return CategoryMapper.convertToDto(savedCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
		Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ProductServiceException(AppConstants.CATEGORY_NOT_FOUND,AppConstants.CATEGORY_NOT_FOUND_ERR_CD));
		
		category.setCategoryName(categoryDto.getCategoryName());
		
		 Category updatedCategory = categoryRepo.save(category);

	
		    return CategoryMapper.convertToDto(updatedCategory);
		
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		
	 List<Category> categories = categoryRepo.findAll();
	 
	 return categories.stream()
             .map(CategoryMapper :: convertToDto)
             .toList();
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
	        
		Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ProductServiceException(AppConstants.CATEGORY_NOT_FOUND,AppConstants.CATEGORY_NOT_FOUND_ERR_CD));
		
		return CategoryMapper.convertToDto(category);
	}

	@Override
	public CategoryDto deleteCategoryById(Integer categoryId) {
		
		Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ProductServiceException(AppConstants.CATEGORY_NOT_FOUND,AppConstants.CATEGORY_NOT_FOUND_ERR_CD));
		
         categoryRepo.deleteById(categoryId);
		
		return CategoryMapper.convertToDto(category);
	} 

}
