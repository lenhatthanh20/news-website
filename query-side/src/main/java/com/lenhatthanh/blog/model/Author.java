package com.lenhatthanh.blog.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Data
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@RedisHash
public class Author {
    @Id
    private String id;

    @Indexed
    @NonNull
    private String name;

    @NonNull
    @Indexed
    String email;
}
