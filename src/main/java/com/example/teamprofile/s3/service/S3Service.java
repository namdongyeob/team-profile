package com.example.teamprofile.s3.service;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {

	private static final Duration PRESIGNED_URL_EXPIRATION = Duration.ofDays(7);

	private final S3Template s3Template;

	@Value("${spring.cloud.aws.s3.bucket}")
	private String bucket;

	@Value("${spring.cloud.aws.s3.bucket}")
	private String cloudFrontDomain;

	public String upload(Long memberId, MultipartFile file) {
		try {
			String key = "uploads/" + memberId + "_" + UUID.randomUUID() + "_" + file.getOriginalFilename();
			s3Template.upload(bucket, key, file.getInputStream());
			log.info("[S3 - LOG] 파일 업로드 성공 - key: {}", key);
			return key;
		} catch (IOException e) {
			log.error("[S3 - LOG] 파일 업로드 실패 - memberId: {}", memberId);
			throw new RuntimeException("파일 업로드 실패", e);
		}
	}
	public String getCloudFrontUrl(String key) {
		String url = "https://" + cloudFrontDomain + "/" + key;
		log.info("[S3 - LOG] CloudFront URL 생성 - key: {}", key);
		return url;
	}
}
