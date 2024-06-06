package br.com.techie.shoppingstore.AP003.infra.exception;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException(String s) {
        super(s);
    }
}
