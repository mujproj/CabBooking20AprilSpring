package com.cabbooking.exception;

public class AdminNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5595964428948080655L;

	public AdminNotFoundException() {
		super();
	}

	public AdminNotFoundException(String message) {
		super(message);
	}
	
}
