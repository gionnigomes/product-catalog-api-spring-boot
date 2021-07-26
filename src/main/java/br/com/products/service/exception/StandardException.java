package br.com.products.service.exception;


import java.io.Serializable;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"status_code", "message"})
public class StandardException implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	private Integer status;
	private String message;
	
	public StandardException() {
		
	}

	public StandardException(Integer status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	@JsonProperty("status_code")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
