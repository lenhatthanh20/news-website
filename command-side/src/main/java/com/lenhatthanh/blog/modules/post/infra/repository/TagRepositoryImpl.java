package com.lenhatthanh.blog.modules.post.infra.repository;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.modules.post.domain.Slug;
import com.lenhatthanh.blog.modules.post.domain.Tag;
import com.lenhatthanh.blog.modules.post.domain.Title;
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
        TagEntity TagEntity = new TagEntity(
                tag.getId().toString(),
                tag.getAggregateVersion(),
                tag.getTitle().getValue(),
                tag.getSlug().getValue()
        );

        tagJpaRepository.save(TagEntity);
    }

    @Override
    public Optional<Tag> findById(String id) {
        Optional<TagEntity> TagEntity = tagJpaRepository.findById(id);
        if (TagEntity.isEmpty()) {
            return Optional.empty();
        }

        Id TagId = new Id(TagEntity.get().getId());
        Long version = TagEntity.get().getVersion();
        Title title = new Title(TagEntity.get().getTitle());
        Slug slug = new Slug(TagEntity.get().getSlug(), title);

        return Optional.of(new Tag(TagId, version, title, slug));
    }

    @Override
    public void delete(Tag tag) {
        tagJpaRepository.deleteById(tag.getId().toString());
    }
}
