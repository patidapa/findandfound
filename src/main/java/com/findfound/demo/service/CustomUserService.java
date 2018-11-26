package com.findfound.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.findfound.demo.filter.CommonUtils;
import com.findfound.demo.model.User;
import com.findfound.demo.repository.UserRepository;
@Service
public class CustomUserService implements UserDetailsService {
	@Autowired
private UserRepository userRepo;
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	User user=userRepo.findByEmail(username);
	System.out.println("User Name is :"+CommonUtils.getUserId(username));
	CustomuserDetails userDetails=null;
	if(user!=null)
	{
		userDetails =new CustomuserDetails();
		userDetails.setUser(user);
	}
	else
	{
		throw new UsernameNotFoundException("user is not exist");
	}
	return userDetails;
}
}
