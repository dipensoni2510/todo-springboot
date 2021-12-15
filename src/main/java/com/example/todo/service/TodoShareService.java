package com.example.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.entity.TodoShare;
import com.example.todo.entity.User;
import com.example.todo.model.ResponseModel;
import com.example.todo.model.TodoShareModel;
import com.example.todo.repository.SharedTodoRepository;

@Service
public class TodoShareService {

	@Autowired
	private SharedTodoRepository sharedTodoRepository;
	
	public ResponseModel createTodoShare(TodoShareModel todoShareModel) {
		ResponseModel responseModel = new ResponseModel();
		TodoShare todoShare = new TodoShare();
		todoShare.setSharedBy(todoShareModel.getSharedByUser());
		todoShare.setSharedTo(todoShareModel.getSharedToUser());
		todoShare.setTodo(todoShareModel.getTodo());
		TodoShare savedTodoShare = sharedTodoRepository.save(todoShare);
		
		if (savedTodoShare != null) {
			responseModel.setCode(200);
    		responseModel.setMessage("Todo shared successfully.!");
    	} else {
    		responseModel.setCode(200);
    		responseModel.setMessage("Something went wrong.!");
    	}
		return responseModel;
	}
	
	public ResponseModel getTodoShared(User user) {
		ResponseModel responseModel = new ResponseModel();
		List<TodoShare> todoShareList = sharedTodoRepository.findBySharedBy(user);
		
		if(todoShareList.size() > 0) {
			responseModel.setCode(200);
    		responseModel.setMessage("Todo Shared List Loaded.!");
    		responseModel.setResult(todoShareList);
    	} else {
    		responseModel.setCode(200);
    		responseModel.setMessage("No connection found.!");
    	}
		return responseModel;
	}
}
