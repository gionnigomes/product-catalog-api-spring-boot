package br.com.products.service.exception;

public class FieldValidatorException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public FieldValidatorException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public FieldValidatorException(String message) {
		super(message);
		
	}

}
