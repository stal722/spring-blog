package io.hexlet.hexlet_spring_blog.controller;

import io.hexlet.hexlet_spring_blog.DefaultAppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Autowired
    private DefaultAppProperties defaultAppProperties;

    @GetMapping("/welcome")
    public String getWelcomeMessage() {
        return defaultAppProperties.getWelcomeMessage();
    }


}
