package com.example.teamprofile.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 팀원 정보를 나타내는 JPA 엔티티 클래스입니다.
 */
@Getter
@Entity
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Integer age;
	
	@Column(nullable = false)
	private String mbti;
	
	@Column
	private String profileImageKey;

	/**
	 * 팀원을 생성하는 생성자입니다.
	 *
	 * @param name 팀원의 이름
	 * @param age  팀원의 나이
	 * @param mbti 팀원의 MBTI
	 */
	public Member(String name, Integer age, String mbti) {
		this.name = name;
		this.age = age;
		this.mbti = mbti;
	}

	/**
	 * 프로필 이미지 키를 업데이트합니다.
	 *
	 * @param profileImageKey 새로운 프로필 이미지 키
	 */
	public void updateProfileImageKey(String profileImageKey) {
		this.profileImageKey = profileImageKey;
	}
}