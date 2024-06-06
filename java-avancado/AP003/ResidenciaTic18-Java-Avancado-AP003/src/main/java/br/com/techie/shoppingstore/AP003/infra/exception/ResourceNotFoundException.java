package br.com.techie.shoppingstore.AP003.infra.exception;

public class ResourceNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public ResourceNotFoundException(String msg) {
    super(msg);
  }
}
