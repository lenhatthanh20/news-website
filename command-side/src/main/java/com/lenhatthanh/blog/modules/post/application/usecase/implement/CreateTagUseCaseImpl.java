package com.lenhatthanh.blog.modules.post.application.usecase.implement;

import com.lenhatthanh.blog.modules.post.application.usecase.CreateTagUseCase;
import com.lenhatthanh.blog.modules.post.domain.entity.Tag;
import com.lenhatthanh.blog.modules.post.domain.service.CreateSlugDomainService;
import com.lenhatthanh.blog.modules.post.domain.valueobject.Title;
import com.lenhatthanh.blog.modules.post.application.repository.TagRepository;
import com.lenhatthanh.blog.modules.post.dto.TagDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateTagUseCaseImpl implements CreateTagUseCase {
    private final TagRepository tagRepository;
    private final CreateSlugDomainService createSlugDomainService;

    public void execute(TagDto tagDto) {
        Tag tag = Tag.create(
                new Title(tagDto.getTitle()),
                createSlugDomainService.createFromTitle(tagDto.getTitle())
        );

        // TODO: Business logic: Tag slug must be unique
        // TODO: Check user and user roles
        tagRepository.save(tag);
    }
}
