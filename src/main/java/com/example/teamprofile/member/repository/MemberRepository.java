package com.example.teamprofile.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.teamprofile.member.entity.Member;

/**
 * 팀원 엔티티에 대한 데이터베이스 접근을 제공하는 리포지토리 인터페이스입니다.
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
}