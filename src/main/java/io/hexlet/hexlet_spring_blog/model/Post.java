package io.hexlet.hexlet_spring_blog.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {
    private String title;
    private String content;
    private boolean published;
}
