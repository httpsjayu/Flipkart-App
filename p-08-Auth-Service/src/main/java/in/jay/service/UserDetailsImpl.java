package in.jay.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.jay.entity.User;
import in.jay.repo.UserRepository;

@Service
public class UserDetailsImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userRepo.findByEmail(email);
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),Collections.emptyList());
	}


}
