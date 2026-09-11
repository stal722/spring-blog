package io.hexlet.hexlet_spring_blog.controller;

import io.hexlet.hexlet_spring_blog.User;
import io.hexlet.hexlet_spring_blog.component.UserMapper;
import io.hexlet.hexlet_spring_blog.dto.UserDTO;
import io.hexlet.hexlet_spring_blog.model.UserEntity;
import io.hexlet.hexlet_spring_blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {


    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserController(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }





    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllUsers() {

        return userRepository.findAll().stream().map(userMapper::toUserDTO).toList();

    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody UserEntity userEntity) {

        var user = userRepository.save(userEntity);

        return userMapper.toUserDTO(user);

    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {

        userRepository.deleteById(id);
    }


}
