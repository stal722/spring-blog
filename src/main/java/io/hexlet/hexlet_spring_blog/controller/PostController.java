package io.hexlet.hexlet_spring_blog.controller;

import io.hexlet.hexlet_spring_blog.component.PostMapper;
import io.hexlet.hexlet_spring_blog.dto.PostCreateDTO;
import io.hexlet.hexlet_spring_blog.dto.PostDTO;
import io.hexlet.hexlet_spring_blog.dto.PostUpdateDTO;
import io.hexlet.hexlet_spring_blog.exception.ResourceNotFoundException;
import io.hexlet.hexlet_spring_blog.model.PostEntity;
import io.hexlet.hexlet_spring_blog.repository.PostRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostController(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @GetMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<PostDTO> index(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createAt").descending());

        return postRepository.findByPublishedTrue(pageable).stream().map(postMapper::toDTO).toList();
    }

    @GetMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDTO showPost(@PathVariable Long id) {
        var post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " Not Found!"));

        return postMapper.toDTO(post);
    }

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDTO createPost(@Valid @RequestBody PostCreateDTO postCreateDTO) {
        var post = postMapper.toEntity(postCreateDTO);
        var savedPost = postRepository.save(post);
        return postMapper.toDTO(savedPost);
    }

    @PutMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDTO updatePost(@Valid @RequestBody PostUpdateDTO data, @PathVariable Long id) {
        var post = postRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException(id + " Not Found"));

        postMapper.updateEntityFromDTO(data, post);

        postRepository.save(post);

        return postMapper.toDTO(post);

    }

    @DeleteMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable Long id) {
        if(!postRepository.existsById(id)) {
            throw new ResourceNotFoundException(id + " Not Found");
        }

        postRepository.deleteById(id);


    }
}
