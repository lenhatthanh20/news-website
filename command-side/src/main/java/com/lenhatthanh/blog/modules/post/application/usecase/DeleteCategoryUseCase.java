package com.lenhatthanh.blog.modules.post.application.usecase;

import com.lenhatthanh.blog.modules.post.domain.service.DeleteCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteCategoryUseCase {
    private DeleteCategoryService deleteCategoryService;

    public void execute(String id) {
        deleteCategoryService.delete(id);
    }
}
