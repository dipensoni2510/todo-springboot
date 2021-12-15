package com.example.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.entity.Comment;
import com.example.todo.entity.Todo;
import com.example.todo.model.CommentModel;
import com.example.todo.model.ResponseModel;
import com.example.todo.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	public ResponseModel saveComment(CommentModel commentModel) {
		ResponseModel responseModel = new ResponseModel();
		Comment comment = new Comment();
		comment.setMessage(commentModel.getMessage());
		comment.setTodo(commentModel.getTodo());
		comment.setUser(commentModel.getCommentedBy());
		
		Comment saveComment = commentRepository.save(comment);
		
		if(saveComment != null) {
			responseModel.setCode(200);
			responseModel.setMessage("Comment saved successfully.!");
		} else {
			responseModel.setCode(200);
			responseModel.setMessage("Something went wrong.!");
		}
		return responseModel;
	}
	
	public ResponseModel getCommentByTodo(Todo todoId) {
		ResponseModel responseModel = new ResponseModel();
		
		List<Comment> getComments = commentRepository.findByTodoId(todoId.getId());

		responseModel.setCode(200);
		if(getComments.size() > 0) {
			System.out.println("In");
			responseModel.setMessage("Comments loaded successfully.!");
			responseModel.setResult(getComments);
		} else {
			responseModel.setMessage("No comments found.!");
		}
		return responseModel;
	}
	
	public Comment getCommentById(Integer id) {
		return commentRepository.getById(id);
	}
	
	public Comment findCommentById(Integer commentId) {
		return commentRepository.findCommentById(commentId);
	}

	public ResponseModel updateComment(Integer commentId, CommentModel commentModel) {
		ResponseModel responseModel = new ResponseModel();
		Comment comment = new Comment();
		comment.setId(commentId);
		comment.setMessage(commentModel.getMessage());
		comment.setTodo(commentModel.getTodo());
		
		Comment saveComment = commentRepository.save(comment);
		
		if(saveComment != null) {
			responseModel.setCode(200);
			responseModel.setMessage("Comment upated successfully.!");
		} else {
			responseModel.setCode(200);
			responseModel.setMessage("Something went wrong.!");
		}
		return responseModel;
	}
	
}
