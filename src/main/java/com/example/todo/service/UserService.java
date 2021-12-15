package com.example.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.entity.User;
import com.example.todo.model.UserModel;
import com.example.todo.repository.UserRepository;
import com.example.todo.util.PasswordHashing;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public String createUser(UserModel userModel) {
		User user = new User();
		
		user.setEmail(userModel.getEmail());
		user.setUsername(userModel.getUsername());
		user.setPassword(PasswordHashing.hashPassword(userModel.getPassword()));
		
		try {
			userRepository.save(user);
			return "User registered successfully";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public User getUser(String username) {
		return userRepository.findByUsername(username);
	}
	
	public User getUserById(Integer userId) {
		return userRepository.findById(userId).orElse(null);
	}
	
}
