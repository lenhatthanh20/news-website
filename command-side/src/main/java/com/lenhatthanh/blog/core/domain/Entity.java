package com.lenhatthanh.blog.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Entity<Type> {
    private Type id;
}
