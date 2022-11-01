package com.grupo3.truequelibre.exceptions;

import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler{
	 Object property;
     String mensaje;
	@ExceptionHandler(value = {Exception.class})
	 public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
		 Map<String,String[]> aux=request.getParameterMap();
	     return new ResponseEntity<>(
	            "puto el que lee", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	 }
	@ExceptionHandler(value = {ConstraintViolationException.class})
	 public ResponseEntity<Object> handleAnyException(ConstraintViolationException ex, WebRequest request) {
		
		for (ConstraintViolation constraintViolation : ex.getConstraintViolations()) {
			Object code=constraintViolation.getConstraintDescriptor().getAnnotation().annotationType().toString();
		}
		 ex.getConstraintViolations().forEach(error-> {
			 property=error.getPropertyPath();
			 mensaje=error.getMessage();
		 });
	     return new ResponseEntity<>(
	            "puto el que lee", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	 }

	
}
