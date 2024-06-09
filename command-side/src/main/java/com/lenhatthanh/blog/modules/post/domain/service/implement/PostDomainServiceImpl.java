package com.lenhatthanh.blog.modules.post.domain.service.implement;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.modules.post.domain.entity.Post;
import com.lenhatthanh.blog.modules.post.domain.event.PostCreatedEvent;
import com.lenhatthanh.blog.modules.post.domain.service.PostDomainService;
import com.lenhatthanh.blog.modules.post.domain.valueobject.PostContent;
import com.lenhatthanh.blog.modules.post.domain.valueobject.Slug;
import com.lenhatthanh.blog.modules.post.domain.valueobject.Summary;
import com.lenhatthanh.blog.modules.post.domain.valueobject.Title;
import com.lenhatthanh.blog.modules.post.dto.PostDto;
import com.lenhatthanh.blog.modules.user.domain.valueobject.PostStatus;
import com.lenhatthanh.blog.shared.UniqueIdGenerator;
import org.springframework.stereotype.Service;

import static com.lenhatthanh.blog.core.domain.AggregateRoot.CONCURRENCY_CHECKING_INITIAL_VERSION;

@Service
public class PostDomainServiceImpl implements PostDomainService {
    @Override
    public Post createNewPost(PostDto postDto) {
        Post post = Post.builder()
                .parentId(postDto.getParentId() != null ? new Id(postDto.getParentId()) : null)
                .userId(new Id(postDto.getUserId()))
                .title(new Title(postDto.getTitle()))
                .metaTitle(postDto.getMetaTitle())
                .content(new PostContent(postDto.getContent()))
                .summary(new Summary(postDto.getSummary()))
                .thumbnail(postDto.getThumbnail())
                .slug(new Slug(postDto.getSlug()))
                .status(PostStatus.DRAFT)
                .build();

        post.setId(new Id(UniqueIdGenerator.create()));
        post.setAggregateVersion(CONCURRENCY_CHECKING_INITIAL_VERSION);
        post.setCategoryIds(postDto.getCategoryIds().stream().map(Id::new).toList());
        post.setTagIds(postDto.getTagIds().stream().map(Id::new).toList());

        post.registerEvent(new PostCreatedEvent(post));
        return post;
    }
}
