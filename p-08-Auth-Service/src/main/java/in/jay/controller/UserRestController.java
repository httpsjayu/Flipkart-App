package in.jay.controller;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.jay.constants.AppConstants;
import in.jay.entity.User;
import in.jay.props.AppProperties;
import in.jay.response.ApiResponse;
import in.jay.service.UserService;

@RestController
public class UserRestController {
	
	private final Logger log = LoggerFactory.getLogger(UserRestController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AppProperties props;
	
	
	@PostMapping("/register")
	public ResponseEntity <ApiResponse> createUser(@RequestParam("user") String userJson, 
			@RequestParam("file") MultipartFile file)throws Exception{
		
          log.info("user registrstion process started");
		
		ApiResponse<User> response = new ApiResponse<>();
		Map<String,String> messages = props.getMessages();
		
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(userJson, User.class);
		User addedUser = userService.addUser(user, file);
		
		if(addedUser != null) {
			response.setStatus(200);
			response.setMessage(messages.get(AppConstants.USER_REG));
			response.setData(addedUser);
		}
		else {
			response.setStatus(500);
			response.setMessage(messages.get(AppConstants.USER_REG_ERR));
		}
		
		log.info("user registration process completed");
		return new ResponseEntity<>(response,HttpStatus.CREATED);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity <User> login(@RequestBody User user){
		
		log.info("login started");
		
		ApiResponse<User> response = new ApiResponse<>();
		Map<String,String> messages = props.getMessages();
		
		User loggedInUser = userService.login(user);
		
		if(loggedInUser != null) {
			response.setStatus(200);
			response.setMessage(messages.get(AppConstants.LOGIN));
			response.setData(loggedInUser);
		}
		else {
			log.error("user login failed");
			response.setStatus(500);
			response.setMessage(messages.get(AppConstants.LOGIN_ERR));
		}
		log.info("Login complted");
		
		return new ResponseEntity<>(loggedInUser,HttpStatus.OK);
	}
	

}
