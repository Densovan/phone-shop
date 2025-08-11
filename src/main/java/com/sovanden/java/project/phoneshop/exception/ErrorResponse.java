package com.sovanden.java.project.phoneshop.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data // Auto-generates getters/setters
@AllArgsConstructor // Generates a constructor for all fields
public class ErrorResponse {
	private HttpStatus status;
	private String message;
}
