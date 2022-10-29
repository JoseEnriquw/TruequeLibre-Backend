package com.grupo3.truequelibre.tools;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;

public class Response <T> {

	T body;
	HttpStatus status;
	HttpHeaders headers;
	List<Error> errors;
	
	

	public Response() {
		errors=new ArrayList<>();
		this.headers= new HttpHeaders();
	}
	
	public Response(HttpStatus status) {
		super();
		this.status = status;
	}



	public Response(T body, HttpStatus status) {
		this.body = body;
		this.status = status;
		errors=new ArrayList<>();
		this.headers= new HttpHeaders();
	}

	public Response(T body, HttpStatus status, MultiValueMap<String, String> headers) {
		this.body = body;
		this.status = status;
		this.headers = HttpHeaders.readOnlyHttpHeaders(headers != null ? headers : new HttpHeaders());
	}

	
	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public HttpHeaders getHeaders() {
		return headers;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void AddError(String code,String property,String message) 
	{
	   errors.add(new Error(code,property,message)); 
	}
	
	public void AddError(Error error) 
	{
	   errors.add(error); 
	}
	
    public void AddErrors(List<Error> error) 
	{
	   errors.addAll(error); 
	}

	public boolean IsValid() {
		return !errors.isEmpty();
	}
	
	public void AddHeaders(MultiValueMap<String, String> headers) 
	{
	  this.headers= HttpHeaders.readOnlyHttpHeaders(headers != null ? headers : new HttpHeaders());
	}
    
    
}
