package in.jay.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.jay.contants.AppConstants;
import in.jay.dto.ProductDto;
import in.jay.entity.Product;
import in.jay.props.AppProps;
import in.jay.response.ApiResponse;
import in.jay.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductRestController {
	
	@Autowired
	private ProductService service;
	
	@Autowired
	private AppProps props;
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse<ProductDto>> addProduct( 
			@RequestBody ProductDto productDto,
	        @RequestParam("file") MultipartFile file){
		
		Map<String,String> messages = props.getMessages();
		ProductDto savedProduct = service.addProduct(productDto,file);
		
		ApiResponse<ProductDto> response = new ApiResponse<>();
		
		if(savedProduct != null) {
		response.setStatusCode(200);
		response.setMessage(messages.get(AppConstants.PRODUCT_ADDED));
		response.setData(savedProduct);
		}
		else {
			response.setStatusCode(500);
			response.setMessage(messages.get(AppConstants.PRODUCT_ADDED_ERR));
		}
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ApiResponse<ProductDto>> updateProduct(@RequestBody ProductDto productDto , @RequestParam MultipartFile file){
		
		Map<String,String> messages = props.getMessages();
		
		ProductDto savedProduct = service.updateProduct(productDto, file);
		
		ApiResponse<ProductDto> response = new ApiResponse<>();
		
		if(savedProduct != null) {
		response.setStatusCode(200);
		response.setMessage(messages.get(AppConstants.PRODUCT_UPDATEED));
		response.setData(savedProduct);
		}
		else {
			response.setStatusCode(500);
			response.setMessage(messages.get(AppConstants.PRODUCT_UPDATED_ERR));
		}
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/products")
	public ResponseEntity<ApiResponse<List<ProductDto>>> getAllProduct(@RequestBody ProductDto productDto){
		
		Map<String,String> messages = props.getMessages();
		
		List<ProductDto> products = service.getAllProduct();
		
		ApiResponse<List<ProductDto>> response = new ApiResponse<>();
		
		if(products != null) {
		response.setStatusCode(200);
		response.setMessage(messages.get(AppConstants.PRODUCT_FETCH));
		response.setData(products);
		}
		else {
			response.setStatusCode(500);
			response.setMessage(messages.get(AppConstants.PRODUCT_FETCH_ERR));
		}
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<ProductDto>> getProductById(@PathVariable("id") Integer productId) {
	    Map<String, String> messages = props.getMessages();
	    ProductDto product = service.getProductById(productId);
	    ApiResponse<ProductDto> response = new ApiResponse<>();

	    if (product != null) {
	        response.setStatusCode(200);
	        response.setMessage(messages.get(AppConstants.PRODUCT_RETRIEVE));
	        response.setData(product);
	    } else {
	        response.setStatusCode(404);
	        response.setMessage(messages.get(AppConstants.PRODUCT_RETRIEVE_ERR));
	    }
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<ProductDto>> deleteProductById(@PathVariable("id") Integer productId) {
	    Map<String, String> messages = props.getMessages();
	    ProductDto product = service.deleteProductById(productId);
	    ApiResponse<ProductDto> response = new ApiResponse<>();

	    if (product != null) {
	        response.setStatusCode(200);
	        response.setMessage(messages.get(AppConstants.PRODUCT_DELETE));
	        response.setData(product);
	    } else {
	        response.setStatusCode(404);
	        response.setMessage(messages.get(AppConstants.PRODUCT_DELETE_ERR));
	    }
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}

	
	@PutMapping("/{productId}/stock/{quantity}")
	public ResponseEntity<ApiResponse<Void>> updateStock(@PathVariable Integer productId,
			@PathVariable Integer quantity) {
		
		Map<String,String> messages = props.getMessages();

		boolean updated = service.updateStock(productId, quantity);

		ApiResponse<Void> response = new ApiResponse<>();

		if (updated) {
			response.setMessage(messages.get(AppConstants.PRODUCT_STOCK));
			response.setStatusCode(200);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setMessage(messages.get(AppConstants.PRODUCT_STOCK_ERR));
			response.setStatusCode(400);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
}
