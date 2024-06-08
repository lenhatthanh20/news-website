package com.lenhatthanh.blog.modules.post.domain;

import com.lenhatthanh.blog.core.domain.Id;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PostUser {
    private Id id;
    private String name;
    private String email;
    private String mobilePhone;
    private Boolean isActive;
    private List<Id> roleIds;

    public static PostUser create(
            Id id,
            String name,
            String email,
            String mobilePhone,
            Boolean isActive,
            List<Id> roleIds
    ) {
        return PostUser.builder()
                .id(id)
                .name(name)
                .email(email)
                .mobilePhone(mobilePhone)
                .isActive(isActive)
                .roleIds(roleIds)
                .build();
    }
}
