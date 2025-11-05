package in.jay.service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.jay.dto.ProductDto;
import in.jay.entity.Product;
import in.jay.mapper.ProductMapper;
import in.jay.repo.ProductRepo;
import in.jay.utils.FileUtils;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;
	
	
	@Override
	public ProductDto addProduct(ProductDto productDto, MultipartFile file) {

		try {

			Product product = ProductMapper.convertToEntity(productDto);

			if (file != null && !file.isEmpty()) {
				String fileName = FileUtils.saveFile(file.getOriginalFilename(), file);
				product.setProductImage(fileName);
			}

			Product savedProduct = productRepo.save(product);

			return ProductMapper.convertToDto(savedProduct);
		} catch (Exception e) {

			throw new RuntimeException("Failed to add product: " + e.getMessage(), e);
		}
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto, MultipartFile file){

	   
	    Product product = productRepo.findById(productDto.getProductId()).orElseThrow(() -> new NoSuchElementException("Product not found for ID: " + productDto.getProductId()));

	     try {
	       if (file != null && !file.isEmpty()) {
	            String fileName = FileUtils.saveFile(file.getName(), file);
	            product.setProductImage(fileName);
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("Failed to update product image: " + e.getMessage(), e);
	    }

	   
	    Product updatedProduct = productRepo.save(product);

	  
	    return ProductMapper.convertToDto(updatedProduct);
	}

	@Override
	public List<ProductDto> getAllProduct() {

		List<Product> products = productRepo.findAll();

		return products.stream().map(ProductMapper::convertToDto).toList();
	}

	@Override
	public ProductDto getProductById(Integer productId) {

		Product product = productRepo.findById(productId).orElseThrow();

		return ProductMapper.convertToDto(product);
	}

	@Override
	public ProductDto deleteProductById(Integer productId) {

		Product product = productRepo.findById(productId).orElseThrow();

		productRepo.deleteById(productId);

		return ProductMapper.convertToDto(product);
	}

	@Override
	public boolean updateStock(Integer productId, Integer quantity) {

		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

		if (product.getStock() < quantity) {
			return false;
		}

		product.setStock(product.getStock() - quantity);

		productRepo.save(product);

		return true;

	}

}
