package io.hexlet.hexlet_spring_blog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDTO {
    @NotBlank
    @Size(min = 5)
    private String email;

    @NotBlank
    @Size(min = 5)
    private String firstName;

    @NotBlank
    @Size(min = 5)
    private String lastName;
}
