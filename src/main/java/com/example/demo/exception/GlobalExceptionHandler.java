package com.example.demo.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

// to handle global exceptions
@ControllerAdvice
public class GlobalExceptionHandler {
	
	// handle specific exception
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	// handle specific exception
		@ExceptionHandler(APIException.class)
		public ResponseEntity<?> handleAPIException(APIException exception, WebRequest request){
			ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
			return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
		}
	
	// handle global exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// handle custom validation exception
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> customValidationErrorHandling(MethodArgumentNotValidException exception) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation error",
				exception.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
}
