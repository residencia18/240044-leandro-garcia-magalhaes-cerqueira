package br.com.techie.shoppingstore.AP003.infra.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message){
        super(message);
    }
}
