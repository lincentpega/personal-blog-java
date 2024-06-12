package com.lincentpega.personalblogjava.domain.post;

import lombok.Builder;

@Builder
public class Post {
    private Long id;
    private PostMetadata metadata;
    private PostContent content;

    public Long id() {
        return id;
    }

    public PostMetadata metadata() {
        return metadata;
    }

    public PostContent content() {
        return content;
    }
}
