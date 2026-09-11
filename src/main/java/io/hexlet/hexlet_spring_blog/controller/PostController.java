package io.hexlet.hexlet_spring_blog.controller;

import io.hexlet.hexlet_spring_blog.component.PostMapper;
import io.hexlet.hexlet_spring_blog.dto.PostDTO;
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

        return postRepository.findByPublishedTrue(pageable).stream().map(postMapper::toPostDTO).toList();
    }

    @GetMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDTO showPost(@PathVariable Long id) {
        var post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " Not Found"));

        return postMapper.toPostDTO(post);
    }

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDTO createPost(@Valid @RequestBody PostEntity postEntity) {

        var post = postRepository.save(postEntity);

        return postMapper.toPostDTO(post);
    }

    @PutMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDTO updatePost(@Valid @RequestBody PostEntity data, @PathVariable Long id) {
        var post = postRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException(id + " Not Found"));

        post.setPublished(data.isPublished());
        post.setContent(data.getContent());
        post.setTitle(data.getTitle());

        postRepository.save(post);

        return postMapper.toPostDTO(post);

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
