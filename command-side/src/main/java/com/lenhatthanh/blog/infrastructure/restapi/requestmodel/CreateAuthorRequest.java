package com.lenhatthanh.blog.infrastructure.restapi.requestmodel;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateAuthorRequest {
    private String name;
    private String email;
}
