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
import org.springframework.web.bind.annotation.RestController;
import in.jay.contants.AppConstants;
import in.jay.dto.CategoryDto;
import in.jay.props.AppProps;
import in.jay.response.ApiResponse;
import in.jay.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryRestController {

    @Autowired
    private CategoryService service;

    @Autowired
    private AppProps props;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<CategoryDto>> addCategory(@RequestBody CategoryDto categoryDto) {
        Map<String, String> messages = props.getMessages();
        CategoryDto addedCategory = service.addCategory(categoryDto);

        ApiResponse<CategoryDto> response = new ApiResponse<>();
        if (addedCategory != null) {
            response.setMessage(messages.get(AppConstants.CATEGORY_ADDED));
            response.setStatusCode(200);
            response.setData(addedCategory);
        } else {
            response.setMessage(messages.get(AppConstants.CATEGORY_ADDED_ERR));
            response.setStatusCode(500);
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDto>> updateCategory(@RequestBody CategoryDto categoryDto,
            @PathVariable("id") Integer categoryId) {

        Map<String, String> messages = props.getMessages();
        CategoryDto updatedCategory = service.updateCategory(categoryDto, categoryId);

        ApiResponse<CategoryDto> response = new ApiResponse<>();
        if (updatedCategory != null) {
            response.setMessage(messages.get(AppConstants.CATEGORY_UPDATEED));
            response.setStatusCode(200);
            response.setData(updatedCategory);
        } else {
            response.setMessage(messages.get(AppConstants.CATEGORY_UPDATED_ERR));
            response.setStatusCode(500);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryDto>>> getAllCategory() {
        Map<String, String> messages = props.getMessages();
        List<CategoryDto> categories = service.getAllCategories();

        ApiResponse<List<CategoryDto>> response = new ApiResponse<>();
        if (categories != null) {
            response.setMessage(messages.get(AppConstants.CATEGORY_FETCH));
            response.setStatusCode(200);
            response.setData(categories);
        } else {
            response.setMessage(messages.get(AppConstants.CATEGORY_FETCH_ERR));
            response.setStatusCode(500);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDto>> getById(@PathVariable Integer id) {
        Map<String, String> messages = props.getMessages();
        CategoryDto category = service.getCategoryById(id);

        ApiResponse<CategoryDto> response = new ApiResponse<>();
        if (category != null) {
            response.setMessage(messages.get(AppConstants.CATEGORY_RETRIEVE));
            response.setStatusCode(200);
            response.setData(category);
        } else {
            response.setMessage(messages.get(AppConstants.CATEGORY_RETRIEVE_ERR));
            response.setStatusCode(500);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDto>> deleteById(@PathVariable Integer id) {
        Map<String, String> messages = props.getMessages();
        CategoryDto category = service.deleteCategoryById(id);

        ApiResponse<CategoryDto> response = new ApiResponse<>();
        if (category != null) {
            response.setMessage(messages.get(AppConstants.CATEGORY_DELETE));
            response.setStatusCode(200);
            response.setData(category);
        } else {
            response.setMessage(messages.get(AppConstants.CATEGORY_DELETE_ERR));
            response.setStatusCode(500);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
