package com.lenhatthanh.blog.modules.post.infra.repository;

import com.lenhatthanh.blog.modules.post.domain.Tag;
import com.lenhatthanh.blog.modules.post.domain.repository.TagRepository;
import com.lenhatthanh.blog.modules.post.infra.repository.entity.TagEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class TagRepositoryImpl implements TagRepository {
    private final TagJpaRepository TagJpaRepository;

    @Override
    public void save(Tag Tag) {
        TagEntity TagEntity = new TagEntity(
                Tag.getId().toString(),
                Tag.getAggregateVersion(),
                Tag.getTitle().getValue(),
                Tag.getSlug().getValue()
        );

        TagJpaRepository.save(TagEntity);
    }
}
