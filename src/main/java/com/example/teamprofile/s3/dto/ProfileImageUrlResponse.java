package com.example.teamprofile.s3.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProfileImageUrlResponse {
	private final String presignedUrl;

	public static ProfileImageUrlResponse from(String url) {
		return new ProfileImageUrlResponse(url);
	}
}