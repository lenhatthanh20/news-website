package com.lenhatthanh.blog.modules.post.application.usecase.implement;

import com.lenhatthanh.blog.modules.post.application.usecase.DeleteTagUseCase;
import com.lenhatthanh.blog.modules.post.domain.Tag;
import com.lenhatthanh.blog.modules.post.domain.exception.TagNotFoundException;
import com.lenhatthanh.blog.modules.post.application.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteTagUseCaseImpl implements DeleteTagUseCase {
    private final TagRepository tagRepository;

    public void execute(String id) {
        Tag tag = tagRepository.findById(id).orElseThrow(TagNotFoundException::new);
        tagRepository.delete(tag);
    }
}
