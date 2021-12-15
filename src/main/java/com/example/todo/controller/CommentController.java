package com.example.todo.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.entity.Comment;
import com.example.todo.entity.Todo;
import com.example.todo.entity.User;
import com.example.todo.model.CommentModel;
import com.example.todo.model.ResponseModel;
import com.example.todo.service.CommentService;
import com.example.todo.service.ConnectionService;
import com.example.todo.service.LoginService;
import com.example.todo.service.TodoService;
import com.example.todo.service.UserService;
import com.example.todo.util.JwtTokenUtil;

@RestController
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LoginService loginService; 
	
	@Autowired
	private TodoService todoService;
	
	@Autowired
	private ConnectionService connectionService; 
	
	@PostMapping("/todo/{todoId}/comment")
	public ResponseModel createComment(@PathVariable Integer todoId, @RequestBody CommentModel commentModel, @RequestHeader("Authorization") String authHeader) throws UnsupportedEncodingException {
		ResponseModel responseModel = new ResponseModel();
		String token = JwtTokenUtil.extractToken(authHeader);
		boolean tokenVerification = loginService.verifyToken(token);
		
		if(tokenVerification) {
			User user = JwtTokenUtil.getDecoded(authHeader);
			System.out.println(userService.getUser(user.getUsername()));
			
			User getUser = userService.getUser(user.getUsername());
			Todo getTodo = todoService.getTodoById(todoId);
			
			if (getTodo.getUser().getId() == getUser.getId() 
					|| connectionService.isUserConnected(getTodo.getUser().getId(), getUser.getId()) != null
					|| connectionService.isUserConnected(getUser.getId(), getTodo.getUser().getId()) != null) {
				commentModel.setCommentedBy(userService.getUser(user.getUsername()));
				commentModel.setTodo(getTodo);
				responseModel = commentService.saveComment(commentModel);
			} else {
				responseModel.setCode(200);
				responseModel.setMessage("You have to connect with this user to post comment.");
			}
		} else {
			responseModel.setCode(401);
			responseModel.setError("Please login to access this request.");
		}
        
		return responseModel;
	}
	
	@GetMapping("/todo/{todoId}/comment")
	public ResponseModel getComments(@PathVariable Integer todoId, @RequestHeader("Authorization") String authHeader) throws UnsupportedEncodingException {
		ResponseModel responseModel = new ResponseModel();
		String token = JwtTokenUtil.extractToken(authHeader);
		boolean tokenVerification = loginService.verifyToken(token);
		
		Todo getTodo = todoService.getTodoById(todoId);
		
		if(tokenVerification) {
			responseModel = commentService.getCommentByTodo(getTodo);
		} else {
			responseModel.setCode(401);
			responseModel.setError("Please login to access this request.");
		}
        
		return responseModel;
	}
	
	@PutMapping("/todo/{todoId}/comment/{commentId}")
	public ResponseModel updateComment(@PathVariable Integer todoId, 
			@PathVariable Integer commentId,
			@RequestBody CommentModel commentModel,
			@RequestHeader("Authorization") String authHeader) throws UnsupportedEncodingException {
		ResponseModel responseModel = new ResponseModel();
		String token = JwtTokenUtil.extractToken(authHeader);
		boolean tokenVerification = loginService.verifyToken(token);
		
		if(tokenVerification) {
			User user = JwtTokenUtil.getDecoded(authHeader);
			
			User getUser = userService.getUser(user.getUsername());
			Todo getTodo = todoService.getTodoById(todoId);
			Comment comment = commentService.getCommentById(commentId);
			
			if (comment.getUser().getId() == getUser.getId()) {
				commentModel.setTodo(getTodo);
				responseModel = commentService.updateComment(commentId, commentModel);
			} else {
				responseModel.setCode(200);
				responseModel.setMessage("You cannot update other users comment.!");
			}
		} else {
			responseModel.setCode(401);
			responseModel.setError("Please login to access this request.");
		}
		return responseModel;
	}
}
