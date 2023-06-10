package com.precize.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserNotExistException.class)
	public ResponseEntity<String> userDoesNotExistExceptionHandler(UserNotExistException me){
		return new ResponseEntity<String>(me.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<String> UserAlreadyExistsExceptionHandler(UserAlreadyExistsException me){
		return new ResponseEntity<String>(me.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidRequestException.class)
	public ResponseEntity<String> InvalidRequestExceptionHandler(InvalidRequestException me){
		return new ResponseEntity<String>(me.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<UserException> userExceptionHandler(MethodArgumentNotValidException me){
		UserException userException = new UserException();
		userException.setTimestamp(LocalDateTime.now());
		userException.setMessage("Validation Error");
		userException.setDetails(me.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<UserException>(userException,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> generalExceptionHandler(Exception me){
		return new ResponseEntity<String>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
