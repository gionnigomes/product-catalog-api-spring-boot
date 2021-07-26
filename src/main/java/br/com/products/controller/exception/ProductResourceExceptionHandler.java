package br.com.products.controller.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.products.service.exception.ProductNotFoundException;
import br.com.products.service.exception.StandardException;

@ControllerAdvice
public class ProductResourceExceptionHandler {
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Object> productNotFound(ProductNotFoundException e, HttpServletRequest req){
		StandardException error = new StandardException(HttpStatus.NOT_FOUND.value(), e.getMessage());
		return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
		
	}

}
