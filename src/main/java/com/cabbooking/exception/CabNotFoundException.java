package com.cabbooking.exception;

public class CabNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9090587718141879101L;

	public CabNotFoundException() {
		super();
	}

	public CabNotFoundException(String message) {
		super(message);
	}

}
