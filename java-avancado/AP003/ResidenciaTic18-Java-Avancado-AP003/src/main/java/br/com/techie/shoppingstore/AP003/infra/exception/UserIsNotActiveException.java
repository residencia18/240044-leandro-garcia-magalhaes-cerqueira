package br.com.techie.shoppingstore.AP003.infra.exception;

public class UserIsNotActiveException extends RuntimeException {
    public UserIsNotActiveException(String s) {
        super(s);
    }
}
