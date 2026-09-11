package io.hexlet.hexlet_spring_blog.component;

import io.hexlet.hexlet_spring_blog.dto.UserDTO;
import io.hexlet.hexlet_spring_blog.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toUserDTO(UserEntity userEntity) {
        UserDTO dto = new UserDTO();
        dto.setId(userEntity.getId());
        dto.setFirstName(userEntity.getFirstName());
        dto.setLastName(userEntity.getLastName());
        dto.setEmail(userEntity.getEmail());

        return dto;
    }
}
