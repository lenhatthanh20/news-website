package com.lenhatthanh.blog.modules.post.domain.service;

import com.lenhatthanh.blog.modules.post.domain.Tag;
import com.lenhatthanh.blog.modules.post.domain.repository.TagRepository;
import com.lenhatthanh.blog.modules.post.domain.exception.TagNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteTagServiceImpl implements DeleteTagService {
    private final TagRepository tagRepository;

    @Override
    public void delete(String id) {
        Tag tag = tagRepository.findById(id).orElseThrow(TagNotFoundException::new);
        tagRepository.delete(tag);
    }
}
