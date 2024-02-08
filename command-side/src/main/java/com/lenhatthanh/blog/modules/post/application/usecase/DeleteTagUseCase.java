package com.lenhatthanh.blog.modules.post.application.usecase;

import com.lenhatthanh.blog.modules.post.domain.service.DeleteTagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteTagUseCase {
    private DeleteTagService deleteTagService;

    public void execute(String id) {
        deleteTagService.delete(id);
    }
}
