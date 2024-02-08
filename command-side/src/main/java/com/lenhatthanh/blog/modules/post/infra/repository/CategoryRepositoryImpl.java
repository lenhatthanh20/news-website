package com.lenhatthanh.blog.modules.post.infra.repository;

import com.lenhatthanh.blog.modules.post.domain.Category;
import com.lenhatthanh.blog.modules.post.domain.repository.CategoryRepository;
import com.lenhatthanh.blog.modules.post.infra.repository.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {
    private final CategoryJpaRepository categoryJpaRepository;

    @Override
    public void save(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity(
                category.getId().toString(),
                category.getAggregateVersion(),
                category.getTitle().getValue(),
                category.getSlug().getValue()
        );

        categoryJpaRepository.save(categoryEntity);
    }

}
