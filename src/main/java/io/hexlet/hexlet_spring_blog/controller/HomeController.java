package io.hexlet.hexlet_spring_blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Добро пожаловать в Spring Blog";
    }

    @GetMapping("/about")
    public String about() {
        return "Это простой Spring blog";
    }
}
