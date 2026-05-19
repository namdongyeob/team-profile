package com.example.teamprofile.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.teamprofile.global.exception.MemberNotFoundException;
import com.example.teamprofile.global.exception.ProfileImageNotFoundException;
import com.example.teamprofile.member.dto.MemberResponse;
import com.example.teamprofile.member.dto.MemberSaveRequest;
import com.example.teamprofile.member.entity.Member;
import com.example.teamprofile.member.repository.MemberRepository;
import com.example.teamprofile.s3.dto.ProfileImageUploadResponse;
import com.example.teamprofile.s3.dto.ProfileImageUrlResponse;
import com.example.teamprofile.s3.service.S3Service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 팀원과 관련된 비즈니스 로직을 처리하는 서비스 클래스입니다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;
	private final S3Service s3Service;

	/**
	 * 새로운 팀원을 저장합니다.
	 *
	 * @param request 저장할 팀원의 정보가 담긴 요청 객체
	 * @return 저장된 팀원의 정보가 담긴 응답 객체
	 */
	@Transactional
	public MemberResponse save(@Valid MemberSaveRequest request) {
		log.info("[API - LOG] 팀원 저장 요청 - name: {}", request.getName());
		Member member = new Member(request.getName(), request.getAge(), request.getMbti());
		Member saved = memberRepository.save(member);
		return MemberResponse.from(saved);
	}

	/**
	 * ID를 통해 특정 팀원을 조회합니다.
	 *
	 * @param id 조회할 팀원의 ID
	 * @return 조회된 팀원의 정보가 담긴 응답 객체
	 * @throws MemberNotFoundException 팀원을 찾을 수 없는 경우 발생
	 */
	@Transactional(readOnly = true)
	public MemberResponse findById(Long id) {
		log.info("[API - LOG] 팀원 조회 요청 - id: {}", id);
		Member member = memberRepository.findById(id)
			.orElseThrow(() -> {
				log.error("[API - LOG] 팀원을 찾을 수 없음 - id: {}", id);
				return new MemberNotFoundException(id);
			});
		return MemberResponse.from(member);
	}

	@Transactional
	public ProfileImageUploadResponse uploadProfileImage(Long id, MultipartFile file) {
		log.info("[API - LOG] 프로필 이미지 업로드 요청 - memberId: {}", id);
		Member member = memberRepository.findById(id)
			.orElseThrow(() -> {
				log.error("[API - LOG] 팀원을 찾을 수 없음 - id: {}", id);
				return new MemberNotFoundException(id);
			});
		String key = s3Service.upload(id, file);
		member.updateProfileImageKey(key);
		return ProfileImageUploadResponse.from(key);
	}

	public ProfileImageUrlResponse getProfileImageUrl(Long id) {
		log.info("[API - LOG] 프로필 이미지 URL 조회 요청 - memberId: {}", id);
		Member member = memberRepository.findById(id)
			.orElseThrow(() -> {
				log.error("[API - LOG] 팀원을 찾을 수 없음 - id: {}", id);
				return new MemberNotFoundException(id);
			});
		if (member.getProfileImageKey() == null) {
			log.error("[API - LOG] 프로필 이미지가 없음 - id: {}", id);
			throw new ProfileImageNotFoundException(id);
		}
		return ProfileImageUrlResponse.from(s3Service.getCloudFrontUrl(member.getProfileImageKey()));
	}
}
