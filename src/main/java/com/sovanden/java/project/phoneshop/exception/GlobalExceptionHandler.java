package com.sovanden.java.project.phoneshop.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// @ControllerAdvice // Registers this class as a global exception handler
// public class GlobalExceptionHandler {
// 	@ExceptionHandler(ApiException.class) // Only handles ApiException, Catches ApiException anywhere in the app
// 	public ResponseEntity<?> handleApiException(ApiException e) {
// 		ErrorResponse errorResponse = new ErrorResponse(e.getStatus(), e.getMessage());
// 		return ResponseEntity.status(e.getStatus()).body(errorResponse);
// 	}
// }
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<?> handleApiException(ApiException e) {
		ErrorResponse errorResponse = new ErrorResponse(e.getStatus(), e.getMessage());
		return ResponseEntity
				.status(e.getStatus())
				.body(errorResponse);
	}

}