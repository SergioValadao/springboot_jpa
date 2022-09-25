package br.com.savsoftware.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	//public ResourceNotFoundException(Object id) {
	public ResourceNotFoundException(String msg) {
		//super("Resource not found. Id " + id);
		super(msg);
	}

}
