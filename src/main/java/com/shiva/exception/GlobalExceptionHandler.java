package com.shiva.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value = RecordNotFoundException.class)
	public ResponseEntity<ExceptionDetails> noRecordFoundExceptionHandler(RecordNotFoundException rnfe, WebRequest request) {
		ExceptionDetails exDetails = new ExceptionDetails(LocalDateTime.now(), rnfe.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(exDetails, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ExceptionDetails> globalExceptionHandler(Exception e, WebRequest request) {
		ExceptionDetails exDetails = new ExceptionDetails(LocalDateTime.now(), e.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(exDetails, HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
