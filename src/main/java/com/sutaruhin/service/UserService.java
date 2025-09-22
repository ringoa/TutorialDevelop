package com.sutaruhin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sutaruhin.entity.User;
import com.sutaruhin.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getUserList(){
		
		return userRepository.findAll();
	}
	
	@Transactional
	public User saveUser(User user) {
		return userRepository.save(user);
	}

}
