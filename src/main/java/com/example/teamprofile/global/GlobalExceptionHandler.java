package com.example.teamprofile.global;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.teamprofile.global.exception.MemberNotFoundException;
import com.example.teamprofile.global.exception.ProfileImageNotFoundException;

import lombok.extern.slf4j.Slf4j;

/**
 * API 전역 예외를 HTTP 응답과 ERROR 로그로 변환합니다.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MemberNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleMemberNotFoundException(MemberNotFoundException e) {
		log.error("[API - ERROR] 팀원을 찾을 수 없습니다.", e);
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body(Map.of("message", e.getMessage()));
	}

	@ExceptionHandler(ProfileImageNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleProfileImageNotFoundException(ProfileImageNotFoundException e) {
		log.error("[API - ERROR] 프로필 이미지가 없습니다.", e);
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body(Map.of("message", e.getMessage()));
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException e) {
		log.error("[API - ERROR] 잘못된 요청입니다.", e);
		return ResponseEntity.badRequest()
			.body(Map.of("message", e.getMessage()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> handleException(Exception e) {
		log.error("[API - ERROR] 서버 오류가 발생했습니다.", e);
		return ResponseEntity.internalServerError()
			.body(Map.of("message", "서버 오류가 발생했습니다."));
	}
}
