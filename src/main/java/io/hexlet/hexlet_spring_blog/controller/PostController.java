package io.hexlet.hexlet_spring_blog.controller;

import io.hexlet.hexlet_spring_blog.model.Post;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PostController {
    private List<Post> posts = new ArrayList<>();

    @GetMapping("/posts")
    public List<Post> index(@RequestParam(defaultValue = "5") Integer limit) {

        return posts.stream().limit(limit).toList();

    }

    @GetMapping("/posts/{id}")
    public Optional<Post> showPost(@PathVariable Integer id) {
        return posts.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst();

    }

    @PostMapping("/posts")
    public Post createPost(@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    @PutMapping("/posts/{id}")
    public Post updatePost(@RequestBody Post data, @PathVariable Integer id) {
        var post = posts.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        post.setAuthor(data.getAuthor());
        post.setContent(data.getContent());
        post.setTitle(data.getTitle());

        return post;

    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable Integer id) {

        posts.removeIf(x -> x.getId().equals(id));

    }
}
