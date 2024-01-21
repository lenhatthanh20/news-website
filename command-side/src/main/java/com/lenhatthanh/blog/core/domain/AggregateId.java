package com.lenhatthanh.blog.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class AggregateId {
    private final String id;

    public String toString() {
        return this.id;
    }
}
