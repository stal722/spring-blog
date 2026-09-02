package io.hexlet.hexlet_spring_blog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Заголовок не должен быть пустым")
    @Size(min = 3, max = 20)
    private String title;

    @NotBlank(message = "Контект не должен быть пустым")
    @Size(min = 3, max = 100)
    private String content;

    private boolean published;
}
