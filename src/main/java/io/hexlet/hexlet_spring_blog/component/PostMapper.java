package io.hexlet.hexlet_spring_blog.component;

import io.hexlet.hexlet_spring_blog.dto.PostCreateDTO;
import io.hexlet.hexlet_spring_blog.dto.PostDTO;
import io.hexlet.hexlet_spring_blog.dto.PostUpdateDTO;
import io.hexlet.hexlet_spring_blog.model.PostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostDTO toDTO(PostEntity postEntity);
    PostEntity toEntity(PostCreateDTO dto);
    void updateEntityFromDTO(PostUpdateDTO dto, @MappingTarget PostEntity postEntity);
}
