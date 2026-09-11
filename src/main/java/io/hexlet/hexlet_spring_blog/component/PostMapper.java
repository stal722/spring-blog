package io.hexlet.hexlet_spring_blog.component;

import io.hexlet.hexlet_spring_blog.dto.PostDTO;
import io.hexlet.hexlet_spring_blog.model.PostEntity;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public PostDTO toPostDTO (PostEntity postEntity) {
        PostDTO dto = new PostDTO();
        dto.setId(postEntity.getId());
        dto.setTitle(postEntity.getTitle());
        dto.setContent(postEntity.getContent());
        dto.setPublished(postEntity.isPublished());
        dto.setCreateAt(postEntity.getCreateAt());
        dto.setUpdateAt(postEntity.getUpdateAt());

        return dto;
    }
}
