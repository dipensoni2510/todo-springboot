package com.example.todo.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.entity.Todo;
import com.example.todo.entity.User;
import com.example.todo.model.ResponseModel;
import com.example.todo.model.TodoShareModel;
import com.example.todo.service.ConnectionService;
import com.example.todo.service.LoginService;
import com.example.todo.service.TodoService;
import com.example.todo.service.TodoShareService;
import com.example.todo.service.UserService;
import com.example.todo.util.JwtTokenUtil;

@RestController
public class TodoSharedController {

	@Autowired
	private TodoShareService todoShareService;
	
	@Autowired
	private LoginService loginService; 
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TodoService todoService;
	
	@Autowired
	private ConnectionService connectionService;
	
	@PostMapping("/todos/{todoId}/share")
	public ResponseModel saveTodoShared(@PathVariable Integer todoId, @RequestBody TodoShareModel todoShareModel, @RequestHeader("Authorization") String authHeader) throws UnsupportedEncodingException {
		ResponseModel responseModel = new ResponseModel();
		String token = JwtTokenUtil.extractToken(authHeader);
		boolean tokenVerification = loginService.verifyToken(token);
		if(tokenVerification) {
			User user = JwtTokenUtil.getDecoded(authHeader);
			
			User sharedTo = userService.getUserById(todoShareModel.getSharedTo());
			Todo getTodo = todoService.getTodoById(todoId);
			
			User shareBy = userService.getUser(user.getUsername());
			System.out.println(sharedTo.getId() + " - "+shareBy.getId() + " - " 
			+ getTodo.getUser().getId() );
			if(getTodo.getUser().getId() == shareBy.getId()
				|| connectionService.isUserConnected(getTodo.getUser().getId(), shareBy.getId()) != null
				|| connectionService.isUserConnected(shareBy.getId(), getTodo.getUser().getId()) != null ) {
				todoShareModel.setSharedByUser(userService.getUser(user.getUsername())); 
				todoShareModel.setSharedToUser(sharedTo);
				todoShareModel.setTodo(getTodo);
				responseModel = todoShareService.createTodoShare(todoShareModel);
			} else {
				responseModel.setCode(200);
				responseModel.setMessage("You have to connect with this user to share his todo.");
			}
		} else {
			responseModel.setCode(401);
			responseModel.setError("Please login to access this request.");
		}
        return responseModel;
	}
	
	@GetMapping("/todos/share")
	public ResponseModel getAllConnections(@RequestHeader("Authorization") String authHeader) throws UnsupportedEncodingException {
		ResponseModel responseModel = new ResponseModel();
		String token = JwtTokenUtil.extractToken(authHeader);
		boolean tokenVerification = loginService.verifyToken(token);
		if(tokenVerification) {
			User user = JwtTokenUtil.getDecoded(authHeader);
			responseModel = todoShareService.getTodoShared(userService.getUser(user.getUsername()));
		} else {
			responseModel.setCode(401);
			responseModel.setError("Please login to access this request.");
		}
        return responseModel;
	}
}
