package com.grupo3.truequelibre.tools;

public class Error {

	private String code;
	private String property;
	private String message;
	
	public Error(String code, String property, String message) {
		this.code = code;
		this.property = property;
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
