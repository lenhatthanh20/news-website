package com.lenhatthanh.blog.modules.post.application.usecase;

import com.lenhatthanh.blog.modules.post.domain.service.DeletePostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeletePostUseCase {
    private DeletePostService deletePostService;

    public void execute(String id) {
        deletePostService.delete(id);
    }
}
