package io.hexlet.hexlet_spring_blog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createAt;


    @LastModifiedDate
    private LocalDateTime updateAt;
}
