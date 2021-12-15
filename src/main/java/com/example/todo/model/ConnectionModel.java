package com.example.todo.model;

import com.example.todo.entity.User;

public class ConnectionModel {
	public Integer id;
	public User connectedBy;
	public String connectedToUserName; 
	public User connectedTo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getConnectedBy() {
		return connectedBy;
	}
	public void setConnectedBy(User connectedBy) {
		this.connectedBy = connectedBy;
	}
	public User getConnectedTo() {
		return connectedTo;
	}
	public void setConnectedTo(User connectedTo) {
		this.connectedTo = connectedTo;
	}
	public String getConnectedToUserName() {
		return connectedToUserName;
	}
	public void setConnectedToUserName(String connectedToUserName) {
		this.connectedToUserName = connectedToUserName;
	}
}
