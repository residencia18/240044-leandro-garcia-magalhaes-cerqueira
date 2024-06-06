package br.com.techie.shoppingstore.AP003.infra.exception;

public class AccessDeniedException extends RuntimeException {

	public AccessDeniedException(String message) {
		super(message);
	}
}
