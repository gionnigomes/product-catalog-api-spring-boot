package br.com.products.controller.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.products.service.exception.StandardException;


@ControllerAdvice
public class ProductValidatorExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> producValidator(MethodArgumentNotValidException e, HttpServletRequest req) {
		StandardException error = new StandardException(HttpStatus.BAD_REQUEST.value(), e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);

	}
	

}
