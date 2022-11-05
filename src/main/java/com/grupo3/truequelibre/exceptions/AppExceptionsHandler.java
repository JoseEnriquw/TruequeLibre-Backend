package com.grupo3.truequelibre.exceptions;

import java.util.ArrayList;
import java.util.List;


import javax.validation.ConstraintViolationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import com.grupo3.truequelibre.tools.Error;

@ControllerAdvice

public class AppExceptionsHandler{
	
	@ExceptionHandler(value = {ConstraintViolationException.class})
	 public ResponseEntity<List<Error>> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
		 List<Error> errors = new ArrayList<>();
		 ex.getConstraintViolations().forEach(error-> {
			 String code=StringUtils.substringAfterLast(error.getConstraintDescriptor().getAnnotation().annotationType().toString(), ".");
			 String property = StringUtils.substringAfterLast(error.getPropertyPath().toString(), ".");
			 String message=error.getMessage();
			 errors.add(new Error(code,property,message));
		 });
	     return new ResponseEntity<List<Error>>(
	            errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	 }
	
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	 public ResponseEntity<List<Error>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
		 List<Error> errors = new ArrayList<>();
		 ex.getBindingResult().getFieldErrors().forEach(error -> {
			 errors.add(new Error(error.getCode(),error.getField(),error.getDefaultMessage()));
		 });
	     return new ResponseEntity<List<Error>>(
	            errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	 }
	
	@ExceptionHandler(value = {Exception.class})
	 public ResponseEntity<?> handleAnyException(Exception ex, WebRequest request) {
		 
	     return new ResponseEntity<>(
	            ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	 }
	
}
