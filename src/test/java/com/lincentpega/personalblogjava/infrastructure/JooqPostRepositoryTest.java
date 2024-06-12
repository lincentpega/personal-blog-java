package com.lincentpega.personalblogjava.infrastructure;

import com.lincentpega.personalblogjava.domain.post.Post;
import com.lincentpega.personalblogjava.domain.post.PostContent;
import com.lincentpega.personalblogjava.domain.post.PostMetadata;
import com.lincentpega.personalblogjava.domain.post.Tag;
import org.jooq.DSLContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jooq.JooqTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.utility.TestcontainersConfiguration;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@JooqTest
@Import(TestcontainersConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("classpath:test-data.sql")
class JooqPostRepositoryTest {

    @Autowired
    private DSLContext ctx;

    @Mock
    JooqTagRepository tagRepository;

    JooqPostRepository postRepository;

    @BeforeEach
    void setUp() {
        when(tagRepository.findById(1L)).thenReturn(Optional.of(new Tag(1L, "tag1")));
        when(tagRepository.findById(2L)).thenReturn(Optional.of(new Tag(2L, "tag2")));
        postRepository = new JooqPostRepository(ctx, tagRepository);
    }

    @Test
    public void shouldCreatePostSuccessfully() {
        // Given
        Post post = Post.builder()
                .id(3L)
                .content(new PostContent("Post body"))
                .metadata(
                        new PostMetadata(
                                "Post title",
                                "Post description",
                                List.of(new Tag(1L, "tag1")),
                                Instant.now(),
                                Instant.now()))
                .build();

        // When
        postRepository.save(post);
    }

    @Test
    public void shouldGetPostById() {
        // When
        Optional<Post> optional = postRepository.findById(1L);

        // Then
        assertThat(optional.isPresent()).isEqualTo(true);
        Post post = optional.get();
        assertThat(post.id()).isEqualTo(1L);
    }
}