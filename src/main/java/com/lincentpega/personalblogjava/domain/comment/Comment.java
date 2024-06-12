package com.lincentpega.personalblogjava.domain.comment;

import lombok.Data;

@Data
public class Comment {
    private Long id;
    private String content;
    private Long postId;
    private String userId;
    private String createdAt;
}
