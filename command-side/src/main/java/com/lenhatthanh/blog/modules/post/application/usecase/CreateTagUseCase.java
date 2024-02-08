package com.lenhatthanh.blog.modules.post.application.usecase;

import com.lenhatthanh.blog.modules.post.domain.service.CreateTagService;
import com.lenhatthanh.blog.modules.post.dto.TagDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateTagUseCase {
    private CreateTagService createTagService;

    public void execute(TagDto tagDto) {
        createTagService.create(tagDto);
    }
}
