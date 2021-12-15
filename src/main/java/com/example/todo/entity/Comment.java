package com.example.todo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "comment")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "todo_id", nullable = false, updatable = false, referencedColumnName = "id", unique = false)
	private Todo todo;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "commented_by", nullable = false, updatable = false, referencedColumnName = "id", unique = false)
	private User user;

	@Column(name = "message")
	private String message;
	
	public Comment() {
		super();
	}

	public Comment(Integer id, Todo todo, User user, String message) {
		super();
		this.id = id;
		this.todo = todo;
		this.user = user;
		this.message = message;
	}

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
