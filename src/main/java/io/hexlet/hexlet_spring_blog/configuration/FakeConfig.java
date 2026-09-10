package io.hexlet.hexlet_spring_blog.configuration;

import net.datafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FakeConfig {

    @Bean
    public Faker getFaker() {
        return new Faker();
    }

}
