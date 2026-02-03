package com.talenttrek.education.exception;

public class EmailInUseException extends RuntimeException {

	public EmailInUseException(String message) {
		super(message);
	}

}