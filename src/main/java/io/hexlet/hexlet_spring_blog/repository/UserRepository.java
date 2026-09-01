package io.hexlet.hexlet_spring_blog.repository;

import io.hexlet.hexlet_spring_blog.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
