package com.example.todo.model;

import com.example.todo.entity.Todo;
import com.example.todo.entity.User;

public class TodoShareModel {
	
	public Integer id;
	public Todo todo;
	public User sharedByUser;
	public User sharedToUser;
	public Integer sharedBy;
	public Integer sharedTo;
	public Integer todoId;
	
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
	public User getSharedByUser() {
		return sharedByUser;
	}
	public void setSharedByUser(User sharedByUser) {
		this.sharedByUser = sharedByUser;
	}
	public User getSharedToUser() {
		return sharedToUser;
	}
	public void setSharedToUser(User sharedToUser) {
		this.sharedToUser = sharedToUser;
	}
	public Integer getSharedBy() {
		return sharedBy;
	}
	public void setSharedBy(Integer sharedBy) {
		this.sharedBy = sharedBy;
	}
	public Integer getSharedTo() {
		return sharedTo;
	}
	public void setSharedTo(Integer sharedTo) {
		this.sharedTo = sharedTo;
	}
	public Integer getTodoId() {
		return todoId;
	}
	public void setTodoId(Integer todoId) {
		this.todoId = todoId;
	}
	
}
