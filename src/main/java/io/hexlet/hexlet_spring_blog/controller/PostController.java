package io.hexlet.hexlet_spring_blog.controller;

import io.hexlet.hexlet_spring_blog.model.Post;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private List<Post> posts = new ArrayList<>();

    @GetMapping("")
    public ResponseEntity<List<Post>> index(@RequestParam(defaultValue = "5") Integer limit) {

        var result = posts.stream().limit(limit).toList();

        return ResponseEntity.ok(result);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> showPost(@PathVariable Integer id) {
        var post = posts.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst();

        return ResponseEntity.of(post);

    }

    @PostMapping("")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        posts.add(post);
        return ResponseEntity
                .created(URI.create("/posts/" + post.getId()))
                .body(post);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@RequestBody Post data, @PathVariable Integer id) {
        var post = posts.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        post.setAuthor(data.getAuthor());
        post.setContent(data.getContent());
        post.setTitle(data.getTitle());

        return ResponseEntity.ok(post);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id) {

        posts.removeIf(x -> x.getId().equals(id));

        return ResponseEntity
                .noContent()
                .build();

    }
}
