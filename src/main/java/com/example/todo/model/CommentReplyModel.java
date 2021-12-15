package com.example.todo.model;

import com.example.todo.entity.Comment;
import com.example.todo.entity.User;

public class CommentReplyModel {

	public Integer id;
	public String reply;
	public Comment comment;
	public User replyBy;
	public Integer commentId;
	public Integer userId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
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
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
