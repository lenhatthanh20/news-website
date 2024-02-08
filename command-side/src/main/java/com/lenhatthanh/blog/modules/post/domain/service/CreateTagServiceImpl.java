package com.lenhatthanh.blog.modules.post.domain.service;

import com.lenhatthanh.blog.modules.post.domain.Tag;
import com.lenhatthanh.blog.modules.post.domain.Title;
import com.lenhatthanh.blog.modules.post.domain.repository.TagRepository;
import com.lenhatthanh.blog.modules.post.dto.TagDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateTagServiceImpl implements CreateTagService {
    private final TagRepository tagRepository;

    @Override
    public void create(TagDto TagDto) {
        Tag tag = Tag.create(new Title(TagDto.getTitle()));
        // TODO: Business logic: Tag slug must be unique

        tagRepository.save(tag);
    }
}
