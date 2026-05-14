package com.example.teamprofile.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.teamprofile.member.dto.MemberResponse;
import com.example.teamprofile.member.dto.MemberSaveRequest;
import com.example.teamprofile.member.service.MemberService;
import com.example.teamprofile.s3.dto.ProfileImageUploadResponse;
import com.example.teamprofile.s3.dto.ProfileImageUrlResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 팀원과 관련된 HTTP 요청을 처리하는 REST 컨트롤러입니다.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
	private final MemberService memberService;

	/**
	 * 새로운 팀원을 등록합니다.
	 *
	 * @param request 등록할 팀원의 정보가 담긴 요청 객체
	 * @return 등록된 팀원의 정보와 함께 200 OK 응답 반환
	 */
	@PostMapping
	public ResponseEntity<MemberResponse> save(@RequestBody @Valid MemberSaveRequest request) {
		log.info("[API - LOG] POST /api/members");
		MemberResponse response = memberService.save(request);
		return ResponseEntity.ok(response);
	}

	/**
	 * ID를 통해 특정 팀원을 조회합니다.
	 *
	 * @param id 조회할 팀원의 ID
	 * @return 조회된 팀원의 정보와 함께 200 OK 응답 반환
	 */
	@GetMapping("/{id}")
	public ResponseEntity<MemberResponse> findById(@PathVariable Long id) {
		log.info("[API - LOG] GET /api/members/{}", id);
		MemberResponse response = memberService.findById(id);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/{id}/profile-image")
	public ResponseEntity<ProfileImageUploadResponse> uploadProfileImage(
		@PathVariable Long id,
		@RequestParam("file") MultipartFile file) {
		log.info("[API - LOG] POST /api/members/{}/profile-image", id);
		return ResponseEntity.ok(memberService.uploadProfileImage(id, file));
	}

	@GetMapping("/{id}/profile-image")
	public ResponseEntity<ProfileImageUrlResponse> getProfileImageUrl(@PathVariable Long id) {
		log.info("[API - LOG] GET /api/members/{}/profile-image", id);
		return ResponseEntity.ok(memberService.getProfileImageUrl(id));
	}
}