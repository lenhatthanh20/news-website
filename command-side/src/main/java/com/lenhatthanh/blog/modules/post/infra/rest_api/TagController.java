package com.lenhatthanh.blog.modules.post.infra.rest_api;

import com.lenhatthanh.blog.modules.post.application.usecase.CreateTagUseCase;
import com.lenhatthanh.blog.modules.post.dto.TagDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tags")
@AllArgsConstructor
public class TagController {
    CreateTagUseCase createTagUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTag(@RequestBody TagDto tagDto) {
        createTagUseCase.execute(tagDto);
    }
}

