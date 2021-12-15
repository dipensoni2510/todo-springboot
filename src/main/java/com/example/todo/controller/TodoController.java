package com.example.todo.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.entity.Todo;
import com.example.todo.entity.User;
import com.example.todo.model.ResponseModel;
import com.example.todo.model.TodoModel;
import com.example.todo.service.LoginService;
import com.example.todo.service.TodoService;
import com.example.todo.service.UserService;
import com.example.todo.util.JwtTokenUtil;

@RestController
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LoginService loginService; 
	
	@PostMapping("/addTodo")
    public ResponseModel addCourse(@RequestBody Todo todo, @RequestHeader("Authorization") String authHeader) throws UnsupportedEncodingException {
		ResponseModel responseModel = new ResponseModel();
		String token = JwtTokenUtil.extractToken(authHeader);
		boolean tokenVerification = loginService.verifyToken(token);
		if(tokenVerification) {
			User user = JwtTokenUtil.getDecoded(authHeader);
			System.out.println(userService.getUser(user.getUsername()));
			todo.setUser(userService.getUser(user.getUsername()));
			responseModel = todoService.saveTodo(todo);
		} else {
			responseModel.setCode(401);
			responseModel.setError("Please login to access this request.");
		}
        return responseModel;
    }

    @GetMapping("/todos")
    public ResponseModel getAllTodos(@RequestHeader("Authorization") String authHeader) throws UnsupportedEncodingException {
    	ResponseModel responseModel = new ResponseModel();
		String token = JwtTokenUtil.extractToken(authHeader);
		boolean tokenVerification = loginService.verifyToken(token);
		
		if(tokenVerification) {
			List<Todo> todoList = todoService.getTodos();
			if(todoList.size() > 0) {
				responseModel.setCode(200);
				responseModel.setResult(todoList);
			} else {
				responseModel.setCode(200);
				responseModel.setMessage("No todo available");
			}
		} else {
			responseModel.setCode(401);
			responseModel.setError("Please login to access this request.");
		}
		
        return responseModel;
    }
    
    @GetMapping("/getTodoById/{id}")
    public ResponseModel findTodoById(@PathVariable int id, @RequestHeader("Authorization") String authHeader) {
    	ResponseModel responseModel = new ResponseModel();
		String token = JwtTokenUtil.extractToken(authHeader);
		boolean tokenVerification = loginService.verifyToken(token);
		
		if(tokenVerification) {
			Todo todo = todoService.getTodoById(id);
			
			if(todo != null) {
				responseModel.setCode(200);
				responseModel.setResult(todo);
			} else {
				responseModel.setCode(200);
				responseModel.setMessage("No todo available");
			}
		} else {
			responseModel.setCode(401);
			responseModel.setError("Please login to access this request.");
		}
		
        return responseModel;
    }
    
    @GetMapping("/getTodoByStatus/{isActive}")
    public ResponseModel findTodoByIsActive(@PathVariable Boolean isActive, @RequestHeader("Authorization") String authHeader) {
    	ResponseModel responseModel = new ResponseModel();
		String token = JwtTokenUtil.extractToken(authHeader);
		boolean tokenVerification = loginService.verifyToken(token);
		
		if(tokenVerification) {
			List<Todo> todoList = todoService.getTodoByIsActive(isActive);
			if(todoList.size() > 0) {
				responseModel.setCode(200);
				responseModel.setResult(todoList);
			} else {
				responseModel.setCode(200);
				responseModel.setMessage("No todo available");
			}
		} else {
			responseModel.setCode(401);
			responseModel.setError("Please login to access this request.");
		}
        return responseModel;
    }
    
    @GetMapping("/getTodoByUserId/{userId}")
    public ResponseModel findTodoByUserId(@PathVariable Integer userId, @RequestHeader("Authorization") String authHeader) {
    	ResponseModel responseModel = new ResponseModel();
		String token = JwtTokenUtil.extractToken(authHeader);
		boolean tokenVerification = loginService.verifyToken(token);
		
		if(tokenVerification) {
			List<Todo> todoList = todoService.getTodoByUserId(userId);
			if(todoList.size() > 0) {
				responseModel.setCode(200);
				responseModel.setResult(todoList);
			} else {
				responseModel.setCode(200);
				responseModel.setMessage("No todo available");
			}
		} else {
			responseModel.setCode(401);
			responseModel.setError("Please login to access this request.");
		}
        return responseModel;
    }

    @PutMapping("/update/todo/{todoId}")
    public ResponseModel updateTodo(@PathVariable Integer todoId, 
    		@RequestBody TodoModel todoModel,  
    		@RequestHeader("Authorization") String authHeader) throws UnsupportedEncodingException {
    	ResponseModel responseModel = new ResponseModel();
		String token = JwtTokenUtil.extractToken(authHeader);
		boolean tokenVerification = loginService.verifyToken(token);
		
		if(tokenVerification) {
			User user = JwtTokenUtil.getDecoded(authHeader);
			User getUser = userService.getUser(user.getUsername());
			Todo getTodo = todoService.getTodoById(todoId);
			
			todoModel.setUser(getTodo.getUser());
			
			if(getUser.getId() == getTodo.getUser().getId()) {
				responseModel = todoService.updateTodo(todoId, todoModel);
			} else {
				responseModel.setCode(200);
				responseModel.setError("You cannot update other users todo.");
			}
		} else {
			responseModel.setCode(401);
			responseModel.setError("Please login to access this request.");
		}

        return responseModel;
    }

    @DeleteMapping("/delete/todo/{todoId}")
    public ResponseModel deleteTodo(@PathVariable int todoId, @RequestHeader("Authorization") String authHeader) throws UnsupportedEncodingException {
    	ResponseModel responseModel = new ResponseModel();
		String token = JwtTokenUtil.extractToken(authHeader);
		boolean tokenVerification = loginService.verifyToken(token);
		
		if(tokenVerification) {
			User user = JwtTokenUtil.getDecoded(authHeader);
			User getUser = userService.getUser(user.getUsername());
			Todo getTodo = todoService.getTodoById(todoId);
			
			if(getUser.getId() == getTodo.getUser().getId()) {
				responseModel = todoService.deleteTodo(todoId);
			}
		} else {
			responseModel.setCode(401);
			responseModel.setError("Please login to access this request.");
		}
        return responseModel;
    }
}
