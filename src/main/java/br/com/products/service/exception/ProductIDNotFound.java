package br.com.products.service.exception;

public class ProductIDNotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProductIDNotFound(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ProductIDNotFound(String message) {
		super(message);
		
	}

	
}
