package com.grupo3.truequelibre.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.grupo3.truequelibre.tools.Response;

public class ControllerBase {

	public ResponseEntity Result(Response response)
	{
		if(response.IsValid())
		{
		   	return RequestError(response);
		}
		
		return RequestSuccess(response);
	}
	
    private ResponseEntity<List<Error>> RequestError(Response response)
    {
    	return new ResponseEntity<List<Error>> (response.getErrors(),response.getHeaders(),response.getStatus());
    }
    
    private  ResponseEntity RequestSuccess(Response response)
    {
    	return new ResponseEntity<> (response.getBody(),response.getHeaders(),response.getStatus());
    }
}
