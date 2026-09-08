package io.hexlet.hexlet_spring_blog.controller;

import io.hexlet.hexlet_spring_blog.exception.ResourceNotFoundException;
import io.hexlet.hexlet_spring_blog.model.Post;
import io.hexlet.hexlet_spring_blog.model.PostEntity;
import io.hexlet.hexlet_spring_blog.repository.PostRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    public Page<PostEntity> index(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createAt").descending());
        return postRepository.findByPublishedTrue(pageable);
    }

    @GetMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostEntity showPost(@PathVariable Long id) {
        var post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " Not Found"));

        return post;
    }

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public PostEntity createPost(@Valid @RequestBody PostEntity postEntity) {
        return postRepository.save(postEntity);
    }

    @PutMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostEntity updatePost(@Valid @RequestBody Post data, @PathVariable Long id) {
        var post = postRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException(id + " Not Found"));

        post.setPublished(data.isPublished());
        post.setContent(data.getContent());
        post.setTitle(data.getTitle());

        postRepository.save(post);

        return post;

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
