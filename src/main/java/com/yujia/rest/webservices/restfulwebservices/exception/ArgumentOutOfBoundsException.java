package com.yujia.rest.webservices.restfulwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ArgumentOutOfBoundsException extends RuntimeException {

	public ArgumentOutOfBoundsException(String message) {
		super(message);
	}
}