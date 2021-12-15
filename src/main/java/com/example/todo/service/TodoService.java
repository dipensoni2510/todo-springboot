package com.example.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.todo.entity.Todo;
import com.example.todo.model.ResponseModel;
import com.example.todo.model.TodoModel;
import com.example.todo.repository.TodoRepository;

@Service
public class TodoService {

	@Autowired
	private TodoRepository todoRepository;
	
    public ResponseModel saveTodo(Todo todo) {
    	ResponseModel responseModel = new ResponseModel();
    	Todo savedTodo = todoRepository.save(todo);
    	if(savedTodo != null) {
    		responseModel.setCode(200);
    		responseModel.setMessage("Todo created successfully.!");

    		return responseModel;
    	}
    	
    	responseModel.setCode(200);
		responseModel.setMessage("Something went wrong.!");
    	
		return responseModel;
    }

    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }
    
    public Todo getTodoById(int id) {
        return todoRepository.findById(id).orElse(null);
    }
    
    public List<Todo> getTodoByIsActive(Boolean isActive) {
        return todoRepository.findByIsActive(isActive);
    }

    public List<Todo> getTodoByUserId(Integer userId) {
        return todoRepository.findByUserId(userId);
    }
    
    public ResponseModel updateTodo(Integer todoId, TodoModel todoModel) {
        System.out.println("updates");
        Todo existingTodo = new Todo();
        
        existingTodo.setId(todoId);
        existingTodo.setTitle(todoModel.getTitle());
        existingTodo.setDescription(todoModel.getDescription());
        existingTodo.setIsActive(todoModel.getIsActive());
        existingTodo.setUser(todoModel.getUser());
        
        ResponseModel responseModel = new ResponseModel();
    	Todo savedTodo = todoRepository.save(existingTodo);
    	if(savedTodo != null) {
    		responseModel.setCode(200);
    		responseModel.setMessage("Todo updated successfully.!");

    		return responseModel;
    	}
    	
    	responseModel.setCode(200);
		responseModel.setMessage("Something went wrong.!");
    	
		return responseModel;
    }

    public ResponseModel deleteTodo(Integer id) {
    	ResponseModel responseModel = new ResponseModel();
        
    	todoRepository.deleteById(id);
        responseModel.setCode(200);
		responseModel.setMessage("Todo deleted successfully.!");

		return responseModel;
    }
	
}
