package com.shiv.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.shiv.entity.User;
import com.shiv.repo.UserRepo;

@Component
public class CustomUserDetails implements UserDetailsService{

	@Autowired
	private UserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("User does not exist");
		}else {
			return new CustomUser(user);
		}
	}

}
