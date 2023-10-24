package com.lenhatthanh.blog.model;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Role {
    @Indexed
    @Id
    private String id;

    @NonNull
    @Indexed
    private String roleId;

    @Indexed
    @NonNull
    private String name;

    @NonNull
    @Indexed
    private String description;

    public Role(@NonNull String id, @NonNull String name, @NonNull String description) {
        this.roleId = id;
        this.name = name;
        this.description = description;
    }
}
