package com.precize.exceptions;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserException {
	
	private LocalDateTime timestamp;
	private String message;
	private String details;
	
	
}
