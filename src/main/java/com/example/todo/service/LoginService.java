package com.example.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.entity.User;
import com.example.todo.model.JwtRequest;
import com.example.todo.model.ResponseModel;
import com.example.todo.repository.UserRepository;
import com.example.todo.util.JwtTokenUtil;
import com.example.todo.util.PasswordHashing;

@Service
public class LoginService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	public ResponseModel validateLogin(JwtRequest jwtRequest) {
		User isFound = userRepository.findByUsername(jwtRequest.getUsername());
		ResponseModel model=null;
		if(isFound != null) {
			boolean checkPassword = PasswordHashing.checkPassword(jwtRequest.getPassword(), isFound.getPassword());
			if(checkPassword) {
				model=new ResponseModel();
				
				String generateToken = jwtTokenUtil.createToken(isFound.getUsername(),jwtRequest.getPassword());
				
				model.setCode(200);
				model.setError("");
				model.setMessage("User Logged in successfully!");
				model.setRequestToken(generateToken);
			}
		} else {
			model=new ResponseModel();
			
			model.setCode(200);
			model.setError("");
			model.setMessage("Incorrect username or password!");
		}
		return model;
	}
	
	public Boolean verifyToken(String token) {
		return JwtTokenUtil.VerifyToken(token);
	}
}
