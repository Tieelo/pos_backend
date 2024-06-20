package org.pos_backend.model.objects;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
				.allowedOrigins(
						"https://posweb.tieelo.de:444",
						"https://oauth.tieelo.de:444",
						"https://pos.tieelo.de:8080",
						"http://localhost:8080",
						"https://pos.tieelo.de:444",
						"http://192.168.10.14:8080",
						"http://[2003:103:818:22a0::51]:8080",
						"http://[2003:103:818:22a0::50]:8080"
				)
				.allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
				.allowedHeaders("Content-Type", "Authorization", "*")
				.allowCredentials(true)
				.maxAge(3600);
	}
}