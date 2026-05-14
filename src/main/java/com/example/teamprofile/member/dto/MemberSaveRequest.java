package com.example.teamprofile.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 팀원 생성 요청을 처리하는 DTO 클래스입니다.
 */
@Getter
@NoArgsConstructor
public class MemberSaveRequest {
	
	/**
	 * 팀원의 이름입니다. 필수 입력값입니다.
	 */
	@NotBlank(message = "이름은 필수입니다")
	private String name;

	/**
	 * 팀원의 나이입니다. 필수 입력값입니다.
	 */
	@NotNull(message = "나이는 필수입니다")
	private Integer age;

	/**
	 * 팀원의 MBTI입니다. 필수 입력값입니다.
	 */
	@NotBlank(message = "MBTI는 필수입니다")
	private String mbti;
}