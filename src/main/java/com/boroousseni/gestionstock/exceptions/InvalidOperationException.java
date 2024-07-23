package com.boroousseni.gestionstock.exceptions;

import lombok.Getter;

public class InvalidOperationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Getter
	private ErrorCode errorCode;

	public InvalidOperationException(String message) {
		super(message);
	}

	public InvalidOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidOperationException(String message, Throwable cause, ErrorCode errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public InvalidOperationException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
}
