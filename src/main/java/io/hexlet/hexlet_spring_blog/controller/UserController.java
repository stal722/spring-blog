package io.hexlet.hexlet_spring_blog.controller;

import io.hexlet.hexlet_spring_blog.component.UserMapper;
import io.hexlet.hexlet_spring_blog.dto.UserCreateDTO;
import io.hexlet.hexlet_spring_blog.dto.UserDTO;
import io.hexlet.hexlet_spring_blog.dto.UserUpdateDTO;
import io.hexlet.hexlet_spring_blog.exception.ResourceNotFoundException;
import io.hexlet.hexlet_spring_blog.model.UserEntity;
import io.hexlet.hexlet_spring_blog.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

        return userRepository.findAll().stream().map(userMapper::toDTO).toList();

    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody UserCreateDTO userCreateDTO) {
        var user = userMapper.toEntity(userCreateDTO);
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO updateUser(@RequestBody UserUpdateDTO dto, @PathVariable long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " Not Found"));

        userMapper.updateEntityFromDTO(dto, user);

        userRepository.save(user);

        return userMapper.toDTO(user);

    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {

        userRepository.deleteById(id);
    }


}
