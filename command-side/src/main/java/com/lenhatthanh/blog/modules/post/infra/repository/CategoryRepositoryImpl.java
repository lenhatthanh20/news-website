package com.lenhatthanh.blog.modules.post.infra.repository;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.modules.post.domain.Category;
import com.lenhatthanh.blog.modules.post.domain.Slug;
import com.lenhatthanh.blog.modules.post.domain.Title;
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
    public Optional<Category> findById(String id) {
        Optional<CategoryEntity> categoryEntity = categoryJpaRepository.findById(id);
        if (categoryEntity.isEmpty()) {
            return Optional.empty();
        }

        Id categoryId = new Id(categoryEntity.get().getId());
        Long version = categoryEntity.get().getVersion();
        Id parentId = categoryEntity.get().getParentId() != null ? new Id(categoryEntity.get().getParentId()) : null;
        Title title = new Title(categoryEntity.get().getTitle());
        Slug slug = new Slug(categoryEntity.get().getSlug(), title);

        return Optional.of(new Category(categoryId, version, parentId, title, slug));
    }

    @Override
    public void delete(Category category) {
        categoryJpaRepository.deleteById(category.getId().toString());
    }
}
