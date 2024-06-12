package com.lincentpega.personalblogjava.infrastructure;

import com.lincentpega.personalblogjava.domain.post.Tag;

import java.util.Optional;

public interface TagRepository {

    void save(Tag tag);

    Optional<Tag> findById(Long id);

    public boolean existsById(Long id);

    void deleteById(Long id);
}
