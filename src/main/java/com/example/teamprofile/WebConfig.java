package com.example.teamprofile;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	private static final String CLOUDFRONT_ORIGIN = "https://d1o99a9x70rw82.cloudfront.net";

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
			.allowedOrigins(CLOUDFRONT_ORIGIN)
			.allowedMethods("GET", "POST", "OPTIONS")
			.allowedHeaders("*");
	}
}
