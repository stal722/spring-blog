package io.hexlet.hexlet_spring_blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HexletSpringBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(HexletSpringBlogApplication.class, args);
	}

}
