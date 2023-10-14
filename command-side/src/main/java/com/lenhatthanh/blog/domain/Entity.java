package com.lenhatthanh.blog.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Entity<Type> {
    public Type id;
}
