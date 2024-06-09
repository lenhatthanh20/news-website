package com.lenhatthanh.blog.model;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User {
    @Indexed
    @Id
    private String id;

    @NonNull
    @Indexed
    private String userId;

    @Indexed
    @NonNull
    private String name;

    @NonNull
    @Indexed
    private String email;

    @NonNull
    private String password;

    @Indexed
    private List<Role> roles = new ArrayList<>();

    public User(@NonNull String id, @NonNull String name, @NonNull String email, @NonNull String password) {
        this.userId = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void addRole(Role role) {
        roles.add(role);
    }
}
