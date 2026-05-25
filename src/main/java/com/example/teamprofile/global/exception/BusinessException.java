package com.example.teamprofile.global.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

	private final ErrorCode errorCode;

	public BusinessException(ErrorCode errorCode, Object... args) {
		super(errorCode.formatMessage(args));
		this.errorCode = errorCode;
	}

	public BusinessException(ErrorCode errorCode, Throwable cause, Object... args) {
		super(errorCode.formatMessage(args), cause);
		this.errorCode = errorCode;
	}
}
