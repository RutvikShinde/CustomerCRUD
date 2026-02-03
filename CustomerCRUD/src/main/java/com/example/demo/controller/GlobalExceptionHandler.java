package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.exception.DuplicateAddressException;
import com.example.demo.exception.DuplicateIdException;
import com.example.demo.exception.DuplicateMobileException;
import com.example.demo.exception.InvalidMobileNumber;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidMobileNumber.class)
	public ResponseEntity<?> invalidMobileNumber(InvalidMobileNumber e){
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT)
		.body(e.getMessage());
		
	}
	
	@ExceptionHandler(DuplicateIdException.class)
	public ResponseEntity<?> duplicateId(DuplicateIdException e){
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(e.getMessage());
	}

	@ExceptionHandler(DuplicateMobileException.class)
	public ResponseEntity<?> duplicateMobile(DuplicateMobileException e){
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(e.getMessage());
	}

	@ExceptionHandler(DuplicateAddressException.class)
	public ResponseEntity<?> duplicateAddress(DuplicateAddressException e){
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(e.getMessage());
	}
}
