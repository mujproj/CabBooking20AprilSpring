package com.cabbooking.exception;

public class DriverNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 914569459626622748L;

	public DriverNotFoundException() {
		super();
	}

	public DriverNotFoundException(String message) {
		super(message);
	}

}
