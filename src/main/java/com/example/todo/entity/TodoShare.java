package com.example.todo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "todoshare")
public class TodoShare {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Todo.class)
	@JoinColumn(name = "todo_id", nullable = false, updatable = false, referencedColumnName = "id", unique = false)
	private Todo todo;
	
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "shared_by", nullable = false, updatable = false, referencedColumnName = "id", unique = false)
    private User sharedBy;
	
    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "shared_to", nullable = false, updatable = false, referencedColumnName = "id", unique = false)
	private User sharedTo;

	public TodoShare() {
		super();
	}

	public TodoShare(Integer id, Todo todo, User sharedBy, User sharedTo) {
		super();
		this.id = id;
		this.todo = todo;
		this.sharedBy = sharedBy;
		this.sharedTo = sharedTo;
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

	public User getSharedBy() {
		return sharedBy;
	}

	public void setSharedBy(User sharedBy) {
		this.sharedBy = sharedBy;
	}

	public User getSharedTo() {
		return sharedTo;
	}

	public void setSharedTo(User sharedTo) {
		this.sharedTo = sharedTo;
	}
	
}
