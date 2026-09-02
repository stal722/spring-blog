package io.hexlet.hexlet_spring_blog.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {
    @NotBlank(message = "Заголовок не должен быть пустым")
    @Size(min = 3, max = 20)
    private String title;

    @NotBlank(message = "Контект не должен быть пустым")
    @Size(min = 3, max = 100)
    private String content;
    private boolean published;
}
