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
@Table(name = "commentreply")
public class CommentReply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@JoinColumn(name = "comment_id", nullable = false, updatable = false, referencedColumnName = "id", unique = false)
	private Comment comment;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@JoinColumn(name = "user_id", nullable = false, updatable = false, referencedColumnName = "id", unique = false)
	private User replyBy;
	
	@Column(name = "reply")
	private String reply;

	public CommentReply() {
		super();
	}

	public CommentReply(Integer id, Comment comment, User replyBy, String reply) {
		super();
		this.id = id;
		this.comment = comment;
		this.replyBy = replyBy;
		this.reply = reply;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public User getReplyBy() {
		return replyBy;
	}

	public void setReplyBy(User replyBy) {
		this.replyBy = replyBy;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}
	
}
