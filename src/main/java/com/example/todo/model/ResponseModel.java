package com.example.todo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude
@JsonPropertyOrder
({
	"requestToken",
	"code",
	"message",
	"error",
	"result"
})
public class ResponseModel {


	@JsonProperty("requestToken")
	private String requestToken; 
	@JsonProperty("code")
	private Integer code;
	
	@JsonProperty("message")
	private String message;
	@JsonProperty("error")
	private String error;
	@JsonProperty("result")
	private Object result;
	
	public String getRequestToken() {
		return requestToken;
	}
	public void setRequestToken(String requestToken) {
		this.requestToken = requestToken;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	

}

