package com.example.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.entity.Connection;
import com.example.todo.entity.User;
import com.example.todo.model.ConnectionModel;
import com.example.todo.model.ResponseModel;
import com.example.todo.repository.ConnectionRepository;

@Service
public class ConnectionService {
	
	@Autowired
	private ConnectionRepository connectionRepository;
	
	public ResponseModel createConnection(ConnectionModel connectionModel) {
		ResponseModel responseModel = new ResponseModel();
		Connection saveConnection = new Connection();
		saveConnection.setConnectedBy(connectionModel.getConnectedBy());
		saveConnection.setConnectedTo(connectionModel.getConnectedTo());
		
		Connection savedConnection = connectionRepository.save(saveConnection);
		
		if (savedConnection != null) {
			responseModel.setCode(200);
    		responseModel.setMessage("Connection created successfully.!");
    	} else {
    		responseModel.setCode(200);
    		responseModel.setMessage("Something went wrong.!");
    	}
		return responseModel;
	}
	
	public ResponseModel getConnection(User user) {
		ResponseModel responseModel = new ResponseModel();
		System.out.println(user.getId());
		List<Connection> connectionList = connectionRepository.findByConnectedBy(user.getId());
		
		if(connectionList.size() > 0) {
			responseModel.setCode(200);
    		responseModel.setMessage("Connection created successfully.!");
    		responseModel.setResult(connectionList);
    	} else {
    		responseModel.setCode(200);
    		responseModel.setMessage("No connection found.!");
    	}
		return responseModel;
	}
	
	public Connection isUserConnected(Integer first, Integer second) {
		return connectionRepository.findByConnectedByAndConnectedTo(first, second);
	}
	
}
