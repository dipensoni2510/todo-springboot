package com.example.todo.model;

import com.example.todo.entity.Todo;
import com.example.todo.entity.User;

public class CommentModel {

	public Integer id;
	public Todo todo;
	public User commentedBy;
	public String message;
	public Integer todoId;
	public Integer commentedId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Todo getTodo() {
		return todo;
	}
	public void setTodo(Todo todo) {
		this.todo = todo;
	}
	public User getCommentedBy() {
		return commentedBy;
	}
	public void setCommentedBy(User optional) {
		this.commentedBy = optional;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getTodoId() {
		return todoId;
	}
	public void setTodoId(Integer todoId) {
		this.todoId = todoId;
	}
	public Integer getCommentedId() {
		return commentedId;
	}
	public void setCommentedId(Integer commentedId) {
		this.commentedId = commentedId;
	}
	
}
