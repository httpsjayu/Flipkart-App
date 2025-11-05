package in.jay.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.jay.entity.User;
import in.jay.repo.UserRepository;
import in.jay.utils.FileUtils;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder pwdEncoder;

	@Autowired 
	private AuthenticationManager authManager;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userRepo.findByEmail(email);
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),Collections.emptyList());
	}

	@Override
	public User addUser(User user, MultipartFile file) throws Exception{
	
		User u = userRepo.findByEmail(user.getEmail());
		
		if(u == null) {
			String fileName = FileUtils.saveFile(file.getName(),file);
			
		     String encodedPwd = pwdEncoder.encode(user.getPassword());
		     user.setPassword(encodedPwd);
			
			user.setUserPic(fileName);
				
				return userRepo.save(user);
		}else {
			return u;
		}
	
	}

	@Override
	public User getUserById(Integer userId) {
	
		return userRepo.findById(userId).orElseThrow();
	}

	@Override
	public List<User> getAllUsers() {
	return 	userRepo.findAll();
		
	}

	@Override
	public User login(User user) {
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()); 
		
		Authentication authenticate = null;
		
		try {
			authenticate = authManager.authenticate(token);
		if(authenticate.isAuthenticated()) {
			return userRepo.findByEmail(user.getEmail());
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public User deleteUserById(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow();
		
		if(user != null) {
		userRepo.deleteById(userId);
		return user;
		}
		else {
			return null;
		}
	
		}
	

}
