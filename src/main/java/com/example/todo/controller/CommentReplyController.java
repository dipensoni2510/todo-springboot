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
import com.example.todo.model.CommentReplyModel;
import com.example.todo.model.ResponseModel;
import com.example.todo.service.CommentReplyService;
import com.example.todo.service.CommentService;
import com.example.todo.service.ConnectionService;
import com.example.todo.service.LoginService;
import com.example.todo.service.TodoService;
import com.example.todo.service.UserService;
import com.example.todo.util.JwtTokenUtil;

@RestController
public class CommentReplyController {

	@Autowired
	private CommentReplyService commentReplyService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LoginService loginService; 
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private ConnectionService connectionService; 
	
	@Autowired
	private TodoService todoService;
	
	@PostMapping("/todo/{todoId}/comment/{commentId}/reply")
	public ResponseModel createComment(@PathVariable Integer todoId, @PathVariable Integer commentId, @RequestBody CommentReplyModel commentReplyModel, @RequestHeader("Authorization") String authHeader) throws UnsupportedEncodingException {
		ResponseModel responseModel = new ResponseModel();
		String token = JwtTokenUtil.extractToken(authHeader);
		boolean tokenVerification = loginService.verifyToken(token);
		
		if(tokenVerification) {
			User user = JwtTokenUtil.getDecoded(authHeader);
			User getUser = userService.getUser(user.getUsername());
			Todo getTodo = todoService.getTodoById(todoId);
			Comment getComment = commentService.getCommentById(commentId);
			
			if (getTodo.getUser().getId() == getUser.getId() 
					|| connectionService.isUserConnected(getTodo.getUser().getId(), getUser.getId()) != null
					|| connectionService.isUserConnected(getComment.getUser().getId(), getUser.getId()) != null
					|| connectionService.isUserConnected(getUser.getId(), getTodo.getUser().getId()) != null
					|| getComment.getUser().getId() == getUser.getId() ) {
				commentReplyModel.setReplyBy(userService.getUser(user.getUsername()));
				commentReplyModel.setComment(commentService.findCommentById(commentId));
				responseModel = commentReplyService.saveCommentReply(commentReplyModel);
			} else {
				responseModel.setCode(200);
				responseModel.setMessage("You have to connect with this user to post reply of this comment.");
			}
		} else {
			responseModel.setCode(401);
			responseModel.setError("Please login to access this request.");
		}
        
		return responseModel;
	}
	
	@GetMapping("/todo/{todoId}/comment/{commentId}/replies")
	public ResponseModel getComments(@PathVariable Integer commentId, @RequestHeader("Authorization") String authHeader) throws UnsupportedEncodingException {
		ResponseModel responseModel = new ResponseModel();
		String token = JwtTokenUtil.extractToken(authHeader);
		boolean tokenVerification = loginService.verifyToken(token);
		
		if(tokenVerification) {
			User user = JwtTokenUtil.getDecoded(authHeader);
			System.out.println(userService.getUser(user.getUsername()));
			responseModel = commentReplyService.getCommentReplyByComment(commentId);
		} else {
			responseModel.setCode(401);
			responseModel.setError("Please login to access this request.");
		}
        
		return responseModel;
	}
	
	@PutMapping("/todo/{todoId}/comment/{commentId}/replies/{replyId}")
	public ResponseModel updateComment(@PathVariable Integer todoId, 
			@PathVariable Integer commentId,
			@PathVariable Integer replyId,
			@RequestBody CommentReplyModel commentReplyModel,
			@RequestHeader("Authorization") String authHeader) throws UnsupportedEncodingException {
		ResponseModel responseModel = new ResponseModel();
		String token = JwtTokenUtil.extractToken(authHeader);
		boolean tokenVerification = loginService.verifyToken(token);
		
		if(tokenVerification) {
			User user = JwtTokenUtil.getDecoded(authHeader);
			
			User getUser = userService.getUser(user.getUsername());
			Comment comment = commentService.getCommentById(commentId);
			
			if (comment.getUser().getId() == getUser.getId()) {
				responseModel = commentReplyService.updateCommentReply(replyId, commentReplyModel);
			} else {
				responseModel.setCode(200);
				responseModel.setMessage("You cannot update other users comment reply.!");
			}
		} else {
			responseModel.setCode(401);
			responseModel.setError("Please login to access this request.");
		}
		return responseModel;
	}
}
