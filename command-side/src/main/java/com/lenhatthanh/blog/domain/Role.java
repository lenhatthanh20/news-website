package com.lenhatthanh.blog.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Role extends Entity <String> {
    private String name;
    private String description;

    public Role(String id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }
}
