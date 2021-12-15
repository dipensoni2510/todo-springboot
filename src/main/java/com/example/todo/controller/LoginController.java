package com.example.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.model.JwtRequest;
import com.example.todo.model.ResponseModel;
import com.example.todo.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
	public ResponseModel validateLogin(@RequestBody JwtRequest jwtRequest) {
		return loginService.validateLogin(jwtRequest);
	}
	
	@PostMapping("/verify-token")
	public boolean verifyToken(@RequestBody ResponseModel responseModel) {
		return loginService.verifyToken(responseModel.getRequestToken());
	}
}
