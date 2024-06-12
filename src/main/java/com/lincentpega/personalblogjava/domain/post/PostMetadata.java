package com.lincentpega.personalblogjava.domain.post;

import java.time.Instant;

public record PostMetadata(
        String title,
        String description,
        Iterable<Tag> tags,
        Instant createdAt,
        Instant updatedAt
) {
}
