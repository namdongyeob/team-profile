package com.example.teamprofile.global.exception;

public class ProfileImageNotFoundException extends RuntimeException {

	public ProfileImageNotFoundException(Long id) {
		super("프로필 이미지가 없습니다. id: " + id);
	}
}
