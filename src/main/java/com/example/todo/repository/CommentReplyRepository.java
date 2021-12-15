package com.example.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todo.entity.CommentReply;

@Repository
public interface CommentReplyRepository extends JpaRepository<CommentReply, Integer> {

	List<CommentReply> findByCommentId(Integer commentId);
	
}
