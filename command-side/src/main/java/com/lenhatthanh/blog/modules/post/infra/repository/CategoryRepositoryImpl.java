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
        CategoryEntity categoryEntity = CategoryEntity.fromDomainModel(category);
        categoryJpaRepository.save(categoryEntity);
    }

    @Override
    public Optional<Category> findById(String id) {
        Optional<CategoryEntity> categoryEntity = categoryJpaRepository.findById(id);
        return categoryEntity.map(CategoryEntity::toDomainModel);
    }

    @Override
    public void delete(Category category) {
        categoryJpaRepository.deleteById(category.getId().toString());
    }
}
