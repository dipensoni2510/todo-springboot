package com.example.todo.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.entity.User;
import com.example.todo.model.ConnectionModel;
import com.example.todo.model.ResponseModel;
import com.example.todo.service.ConnectionService;
import com.example.todo.service.LoginService;
import com.example.todo.service.UserService;
import com.example.todo.util.JwtTokenUtil;

@RestController
public class ConnectionController {
	
	@Autowired
	private ConnectionService connectionService;
	
	@Autowired
	private LoginService loginService; 
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/users/create-connection")
	public ResponseModel createConnection(@RequestBody ConnectionModel connectionModel, 
			@RequestHeader("Authorization") String authHeader) throws UnsupportedEncodingException {
		ResponseModel responseModel = new ResponseModel();
		String token = JwtTokenUtil.extractToken(authHeader);
		boolean tokenVerification = loginService.verifyToken(token);
		if(tokenVerification) {
			User user = JwtTokenUtil.getDecoded(authHeader);
			connectionModel.setConnectedBy(userService.getUser(user.getUsername()));
			connectionModel.setConnectedTo(userService.getUser(connectionModel.getConnectedToUserName()));
			responseModel = connectionService.createConnection(connectionModel);
		} else {
			responseModel.setCode(401);
			responseModel.setError("Please login to access this request.");
		}
        return responseModel;
	}
	
	@GetMapping("/users/connections")
	public ResponseModel getAllConnections(@RequestHeader("Authorization") String authHeader) throws UnsupportedEncodingException {
		ResponseModel responseModel = new ResponseModel();
		String token = JwtTokenUtil.extractToken(authHeader);
		boolean tokenVerification = loginService.verifyToken(token);
		if(tokenVerification) {
			User user = JwtTokenUtil.getDecoded(authHeader);
			responseModel = connectionService.getConnection(userService.getUser(user.getUsername()));
		} else {
			responseModel.setCode(401);
			responseModel.setError("Please login to access this request.");
		}
        return responseModel;
	}
	
}
