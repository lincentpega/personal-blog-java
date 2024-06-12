package com.lincentpega.personalblogjava.infrastructure;

import com.lincentpega.personalblogjava.domain.post.Post;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository {

    void save(Post post);

    Optional<Post> findById(Long id);

    void deleteById(Long id);
}
