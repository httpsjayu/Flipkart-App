package in.jay.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import in.jay.dto.ProductDto;

public interface ProductService {
	
	public ProductDto addProduct(ProductDto productDto, MultipartFile file);
	
	public ProductDto updateProduct(ProductDto productDto,MultipartFile file);
	
	public List<ProductDto> getAllProduct();
	
	public ProductDto getProductById(Integer productId);
	
	public ProductDto deleteProductById(Integer productId);
	
	public boolean updateStock(Integer productId,Integer quantity);

}
