package com.example.teamprofile.member.dto;

import com.example.teamprofile.member.entity.Member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 팀원 정보를 반환하는 DTO(Data Transfer Object) 클래스입니다.
 */
@Getter
@RequiredArgsConstructor
public class MemberResponse {
	private final Long id;
	private final String name;
	private final Integer age;
	private final String mbti;
	private final String profileImageKey;

	/**
	 * Member 엔티티를 MemberResponse DTO로 변환합니다.
	 *
	 * @param member 변환할 Member 엔티티
	 * @return 변환된 MemberResponse 객체
	 */
	public static MemberResponse from(Member member) {
		return new MemberResponse(
				member.getId(),
				member.getName(),
				member.getAge(),
				member.getMbti(),
				member.getProfileImageKey());
	}
}