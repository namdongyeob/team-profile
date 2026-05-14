package com.example.teamprofile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 팀 프로필 애플리케이션의 메인 클래스입니다.
 * Spring Boot 애플리케이션의 시작점 역할을 합니다.
 */
@SpringBootApplication
public class TeamProfileApplication {

	/**
	 * 애플리케이션을 실행하는 메인 메서드입니다.
	 *
	 * @param args 실행 시 전달받는 커맨드라인 인수
	 */
	public static void main(String[] args) {
		SpringApplication.run(TeamProfileApplication.class, args);
	}
}