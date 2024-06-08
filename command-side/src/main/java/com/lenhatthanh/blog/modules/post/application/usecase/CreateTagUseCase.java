package com.lenhatthanh.blog.modules.post.application.usecase;

import com.lenhatthanh.blog.modules.post.dto.TagDto;

public interface CreateTagUseCase {
    public void execute(TagDto tagDto);
}
