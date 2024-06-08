package com.lenhatthanh.blog.modules.post.application.usecase.implement;

import com.lenhatthanh.blog.modules.post.application.usecase.CreateTagUseCase;
import com.lenhatthanh.blog.modules.post.domain.Tag;
import com.lenhatthanh.blog.modules.post.domain.Title;
import com.lenhatthanh.blog.modules.post.application.repository.TagRepository;
import com.lenhatthanh.blog.modules.post.domain.service.CreateSlugFromTitleService;
import com.lenhatthanh.blog.modules.post.dto.TagDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateTagUseCaseImpl implements CreateTagUseCase {
    private final TagRepository tagRepository;
    private final CreateSlugFromTitleService createSlugFromTitleService;

    public void execute(TagDto tagDto) {
        Tag tag = Tag.create(
                new Title(tagDto.getTitle()),
                createSlugFromTitleService.create(tagDto.getTitle())
        );

        // TODO: Business logic: Tag slug must be unique
        tagRepository.save(tag);
    }
}
