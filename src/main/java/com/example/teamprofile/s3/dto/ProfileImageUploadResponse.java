package com.example.teamprofile.s3.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProfileImageUploadResponse {
	private final String key;

	public static ProfileImageUploadResponse from(String key) {
		return new ProfileImageUploadResponse(key);
	}
}