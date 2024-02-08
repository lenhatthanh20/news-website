package com.lenhatthanh.blog.modules.post.infra.repository;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.modules.post.domain.*;
import com.lenhatthanh.blog.modules.post.domain.repository.PostRepository;
import com.lenhatthanh.blog.modules.post.infra.repository.entity.PostEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@AllArgsConstructor
public class PostRepositoryImpl implements PostRepository {
    private PostJpaRepository postJpaRepository;

    @Override
    public void save(Post post) {
        PostEntity postEntity = new PostEntity(
                post.getId().toString(),
                post.getAggregateVersion(),
                post.getTitle().getValue(),
                post.getMetaTitle(),
                post.getContent().getValue(),
                post.getSummary().getValue(),
                post.getThumbnail(),
                post.getSlug().getValue(),
                post.getPublishedAt()
        );

        postEntity.setUserId(post.getUserId().toString());

        Set<String> categoryIds = new HashSet<>();
        post.getCategoryIds().forEach(categoryId -> categoryIds.add(categoryId.toString()));
        postEntity.setCategoryIds(categoryIds);

        Set<String> tagIds = new HashSet<>();
        post.getTagIds().forEach(tagId -> tagIds.add(tagId.toString()));
        postEntity.setTagIds(tagIds);

        this.postJpaRepository.save(postEntity);
    }

    @Override
    public Optional<Post> findById(String id) {
        Optional<PostEntity> postEntity = this.postJpaRepository.findById(id);
        if (postEntity.isEmpty()) {
            return Optional.empty();
        }

        PostEntity post = postEntity.get();
        Id parentId = post.getParentId() != null ? new Id(post.getParentId()) : null;
        return Optional.of(
                new Post(
                        new Id(post.getId()),
                        post.getVersion(),
                        parentId,
                        new Title(post.getTitle()),
                        post.getMetaTitle(),
                        new PostContent(post.getContent()),
                        new Id(post.getUser().getId()),
                        new Summary(post.getSummary()),
                        post.getThumbnail(),
                        new Slug(post.getSlug(), new Title(post.getTitle())),
                        post.getPublishedAt()
                )
        );
    }
}
