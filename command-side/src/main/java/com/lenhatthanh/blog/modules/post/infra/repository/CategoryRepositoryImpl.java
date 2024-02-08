package com.lenhatthanh.blog.modules.post.infra.repository;

import com.lenhatthanh.blog.modules.post.domain.Category;
import com.lenhatthanh.blog.modules.post.domain.repository.CategoryRepository;
import com.lenhatthanh.blog.modules.post.infra.repository.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

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

        if (category.getParentId() != null) {
            categoryEntity.setParentId(category.getParentId().toString());
        }

        categoryJpaRepository.save(categoryEntity);
    }

    @Override
    public Optional<CategoryEntity> findById(String id) {
        return categoryJpaRepository.findById(id);
    }
}
