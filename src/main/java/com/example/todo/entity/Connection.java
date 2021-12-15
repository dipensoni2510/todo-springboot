package com.example.todo.entity;

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
@Table(name = "connection")
public class Connection {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
    @OneToOne(optional = false)
    @JoinColumn(name = "connected_by", nullable = false, updatable = false, referencedColumnName = "id", unique = false)
    private User connectedBy;
	
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "connected_to", nullable = false, updatable = false, referencedColumnName = "id", unique = false)
	private User connectedTo;

	public Connection() {
		super();
	}

	public Connection(Integer id, User connectedBy, User connectedTo) {
		super();
		this.id = id;
		this.connectedBy = connectedBy;
		this.connectedTo = connectedTo;
	}

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
   	
}
