package io.hexlet.hexlet_spring_blog.component;

import io.hexlet.hexlet_spring_blog.dto.UserCreateDTO;
import io.hexlet.hexlet_spring_blog.dto.UserDTO;
import io.hexlet.hexlet_spring_blog.dto.UserUpdateDTO;
import io.hexlet.hexlet_spring_blog.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(UserEntity userEntity);
    UserEntity toEntity(UserCreateDTO dto);
    void updateEntityFromDTO(UserUpdateDTO dto, @MappingTarget UserEntity userEntity);
}
