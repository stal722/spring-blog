package io.hexlet.hexlet_spring_blog.controller;

import io.hexlet.hexlet_spring_blog.model.Post;
import io.hexlet.hexlet_spring_blog.model.PostEntity;
import io.hexlet.hexlet_spring_blog.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public List<PostEntity> index() {
        return postRepository.findAll();
    }

    @GetMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostEntity showPost(@PathVariable Long id) {
        var post = postRepository.findById(id).get();
        return post;


    }

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public PostEntity createPost(@RequestBody PostEntity postEntity) {
        postRepository.save(postEntity);
        return postEntity;
    }

    @PutMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostEntity updatePost(@RequestBody Post data, @PathVariable Long id) {
        var post = postRepository.findById(id).get();

        post.setPublished(data.isPublished());
        post.setContent(data.getContent());
        post.setTitle(data.getTitle());

        postRepository.save(post);

        return post;

    }

    @DeleteMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable Long id) {

        postRepository.deleteById(id);

    }
}
