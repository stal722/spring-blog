package io.hexlet.hexlet_spring_blog.model;

import io.hexlet.hexlet_spring_blog.repository.PostRepository;
import io.hexlet.hexlet_spring_blog.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

@Component
public class ModelGenerator {

    private final Faker faker;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public ModelGenerator(Faker faker, UserRepository userRepository, PostRepository postRepository) {
        this.faker = faker;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @PostConstruct
    public void generateData() {
        for (int i = 0; i < 5; i++) {
            var userEntity = new UserEntity();
            userEntity.setEmail(faker.internet().emailAddress());
            userEntity.setFirstName(faker.name().firstName());
            userEntity.setLastName(faker.name().lastName());
            userEntity.setBirthday(faker.timeAndDate().birthday());
            userRepository.save(userEntity);

            var postEntity = new PostEntity();
            postEntity.setTitle(faker.book().title());
            postEntity.setContent(faker.lorem().characters(3,199));
            postEntity.setPublished(faker.bool().bool());
            postRepository.save(postEntity);
        }

    }
}
