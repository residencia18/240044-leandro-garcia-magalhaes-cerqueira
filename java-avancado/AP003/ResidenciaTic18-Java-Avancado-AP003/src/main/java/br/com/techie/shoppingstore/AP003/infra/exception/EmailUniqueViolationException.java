package br.com.techie.shoppingstore.AP003.infra.exception;

public class EmailUniqueViolationException extends RuntimeException {

    public EmailUniqueViolationException(String message) {
        super(message);
    }
}
