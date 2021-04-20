package com.cabbooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptions {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(CustomerNotFoundException.class)
	public String handleCustomerNotFound(CustomerNotFoundException e) {
		return e.getMessage();
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(CabNotFoundException.class)
	public String handleCabNotFound(CabNotFoundException e) {
		return e.getMessage();
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(AdminNotFoundException.class)
	public String handleAdminNotFound(AdminNotFoundException e) {
		return e.getMessage();
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(DriverNotFoundException.class)
	public String handleDriverNotFound(DriverNotFoundException e) {
		return e.getMessage();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(RuntimeException.class)
	public String handleRuntimeException(RuntimeException e) {
		return e.getMessage();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public String handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		return e.getMessage();
	}
}
