package com.lenhatthanh.blog.modules.post.application.usecase;

import com.lenhatthanh.blog.modules.post.domain.Tag;
import com.lenhatthanh.blog.modules.post.domain.exception.TagNotFoundException;
import com.lenhatthanh.blog.modules.post.domain.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteTagUseCase {
    private final TagRepository tagRepository;

    public void execute(String id) {
        Tag tag = tagRepository.findById(id).orElseThrow(TagNotFoundException::new);
        tagRepository.delete(tag);
    }
}
