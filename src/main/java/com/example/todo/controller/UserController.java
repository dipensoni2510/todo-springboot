package com.example.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.model.UserModel;
import com.example.todo.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/users/register")
	public ResponseEntity<String> registerUser(@RequestBody UserModel userModel) {
		String user = userService.createUser(userModel);	
		return !user.isEmpty() ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<String>(user, HttpStatus.OK) ;
	}
	
}
