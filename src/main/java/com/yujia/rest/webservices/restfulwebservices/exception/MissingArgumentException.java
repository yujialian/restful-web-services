package com.yujia.rest.webservices.restfulwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class MissingArgumentException extends RuntimeException {

	public MissingArgumentException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}