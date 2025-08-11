package com.sovanden.java.project.phoneshop.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor // Generates constructor for final fields
public class ApiException extends RuntimeException {
	private final HttpStatus status;
	private final String message;
}
