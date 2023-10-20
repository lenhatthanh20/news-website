package com.lenhatthanh.blog.model;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@RequiredArgsConstructor(staticName = "of")
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
}
