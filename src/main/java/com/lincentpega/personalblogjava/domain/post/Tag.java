package com.lincentpega.personalblogjava.domain.post;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Tag {
    private Long id;
    private String name;

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }
}
