package com.boroousseni.gestionstock.exceptions;

import lombok.Getter;

public class EntityNotFoundException extends RuntimeException {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	@Getter
	private ErrorCode errorCode;

	public EntityNotFoundException(String message) {
		super(message);
	}

	public EntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public EntityNotFoundException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public EntityNotFoundException(String message, Throwable cause, ErrorCode errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}


}
