package com.example.teamprofile.global;

import java.util.Map;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.teamprofile.global.exception.BusinessException;
import com.example.teamprofile.global.exception.ErrorCode;

import lombok.extern.slf4j.Slf4j;

/**
 * API 전역 예외를 HTTP 응답과 ERROR 로그로 변환합니다.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Map<String, String>> handleBusinessException(BusinessException e) {
		ErrorCode errorCode = e.getErrorCode();
		log.error("[API - ERROR] {}", e.getMessage(), e);
		return ResponseEntity.status(errorCode.getStatus())
			.body(createErrorResponse(errorCode, e.getMessage()));
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException e) {
		ErrorCode errorCode = ErrorCode.INVALID_REQUEST;
		log.error("[API - ERROR] {}", e.getMessage(), e);
		return ResponseEntity.status(errorCode.getStatus())
			.body(createErrorResponse(errorCode, e.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		ErrorCode errorCode = ErrorCode.INVALID_REQUEST;
		String message = e.getBindingResult()
			.getFieldErrors()
			.stream()
			.findFirst()
			.map(DefaultMessageSourceResolvable::getDefaultMessage)
			.orElse(errorCode.getMessage());
		log.error("[API - ERROR] {}", message, e);
		return ResponseEntity.status(errorCode.getStatus())
			.body(createErrorResponse(errorCode, message));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> handleException(Exception e) {
		ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
		log.error("[API - ERROR] {}", errorCode.getMessage(), e);
		return ResponseEntity.status(errorCode.getStatus())
			.body(createErrorResponse(errorCode, errorCode.getMessage()));
	}

	private Map<String, String> createErrorResponse(ErrorCode errorCode, String message) {
		return Map.of(
			"code", errorCode.name(),
			"message", message
		);
	}
}
