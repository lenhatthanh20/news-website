package com.lenhatthanh.blog.modules.post.infra.repository;

import com.lenhatthanh.blog.modules.post.domain.Tag;
import com.lenhatthanh.blog.modules.post.domain.repository.TagRepository;
import com.lenhatthanh.blog.modules.post.infra.repository.entity.TagEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@AllArgsConstructor
public class TagRepositoryImpl implements TagRepository {
    private final TagJpaRepository tagJpaRepository;

    @Override
    public void save(Tag tag) {
        TagEntity tagEntity = TagEntity.fromDomainModel(tag);
        tagJpaRepository.save(tagEntity);
    }

    @Override
    public Optional<Tag> findById(String id) {
        Optional<TagEntity> tagEntity = tagJpaRepository.findById(id);

        return tagEntity.map(TagEntity::toDomainModel);
    }

    @Override
    public void delete(Tag tag) {
        tagJpaRepository.deleteById(tag.getId().toString());
    }
}
