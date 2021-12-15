package com.example.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.entity.CommentReply;
import com.example.todo.model.CommentReplyModel;
import com.example.todo.model.ResponseModel;
import com.example.todo.repository.CommentReplyRepository;

@Service
public class CommentReplyService {

	@Autowired
	private CommentReplyRepository commentReplyRepository;
	
	public ResponseModel saveCommentReply(CommentReplyModel commentReplyModel) {
		ResponseModel responseModel = new ResponseModel();
		CommentReply commentReply = new CommentReply();
		
		commentReply.setReply(commentReplyModel.getReply());
		commentReply.setComment(commentReplyModel.getComment());
		commentReply.setReplyBy(commentReplyModel.getReplyBy());
		
		CommentReply saveCommentReply = commentReplyRepository.save(commentReply);
		
		if(saveCommentReply != null) {
			responseModel.setCode(200);
			responseModel.setMessage("Comment reply saved successfully.!");
		} else {
			responseModel.setCode(200);
			responseModel.setMessage("Something went wrong.!");
		}
		return responseModel;
	}
	
	public ResponseModel getCommentReplyByComment(Integer commetnId) {
		ResponseModel responseModel = new ResponseModel();
		List<CommentReply> getCommentReplies = commentReplyRepository.findByCommentId(commetnId);
		
		System.out.println("commentId : "+commetnId);
		
		responseModel.setCode(200);
		if(getCommentReplies.size() > 0) {
			responseModel.setMessage("Comment replies loaded successfully.!");
			responseModel.setResult(getCommentReplies);
		} else {
			responseModel.setMessage("No comment reply found.!");
		}
		return responseModel;
	}
	
	public ResponseModel updateCommentReply(Integer replyId, CommentReplyModel commentReplyModel) {
		ResponseModel responseModel = new ResponseModel();
		CommentReply commentReply = new CommentReply();
		commentReply.setId(replyId);
		commentReply.setReply(commentReplyModel.getReply());
		
		commentReplyRepository.save(commentReply);
		
		responseModel.setCode(200);
		responseModel.setMessage("Comment reply upated successfully.!");
		
		return responseModel;
	}
	
}
