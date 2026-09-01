package io.hexlet.hexlet_spring_blog.controller;

import io.hexlet.hexlet_spring_blog.User;
import io.hexlet.hexlet_spring_blog.model.UserEntity;
import io.hexlet.hexlet_spring_blog.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserEntity> getAllUsers() {

        return userRepository.findAll();

    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity createUser(@RequestBody UserEntity userEntity) {

        userRepository.save(userEntity);

        return userEntity;

    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {

        userRepository.deleteById(id);
    }


}
