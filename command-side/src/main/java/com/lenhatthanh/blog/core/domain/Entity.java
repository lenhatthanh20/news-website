package com.lenhatthanh.blog.core.domain;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
public class Entity<Type> {
    private Type id;
}
