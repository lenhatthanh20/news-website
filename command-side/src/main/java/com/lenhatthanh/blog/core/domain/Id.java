package com.lenhatthanh.blog.core.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Id {
    private final String id;

    public String toString() {
        return this.id;
    }
}
