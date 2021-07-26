package br.com.products.controller.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.products.service.exception.FieldValidatorException;
import br.com.products.service.exception.StandardException;

@ControllerAdvice
public class ProductIDValidatorExceptionHandler {
	
	@ExceptionHandler(FieldValidatorException.class)
	public ResponseEntity<Object> productIDNotFound(FieldValidatorException e, HttpServletRequest req){
		StandardException error = new StandardException(HttpStatus.NOT_FOUND.value(), e.getMessage());
		return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
		
	}

}
