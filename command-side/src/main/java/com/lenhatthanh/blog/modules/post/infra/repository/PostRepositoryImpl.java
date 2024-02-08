package com.lenhatthanh.blog.modules.post.infra.repository;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.modules.post.domain.*;
import com.lenhatthanh.blog.modules.post.domain.repository.PostRepository;
import com.lenhatthanh.blog.modules.post.infra.repository.entity.PostEntity;
import com.lenhatthanh.blog.modules.post.infra.repository.entity.CommentEntity;
import com.lenhatthanh.blog.modules.user.infra.repository.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class PostRepositoryImpl implements PostRepository {
    private PostJpaRepository postJpaRepository;

    @Override
    public void save(Post post) {
        // I don't want to fetch all UserEntity from database
        UserEntity user = new UserEntity();
        user.setId(post.getUserId().toString());

        PostEntity postEntity = new PostEntity(
                post.getId().toString(),
                post.getTitle().getValue(),
                post.getContent().getValue(),
                user,
                post.getSummary().getValue(),
                post.getThumbnail(),
                post.getSlug().getValue(),
                post.getPublishedAt(),
                post.getAggregateVersion()
        );

        List<CommentEntity> commentEntities = new ArrayList<>();
        post.getComments().stream().map(
                comment -> {
                    UserEntity commentUser = new UserEntity();
                    commentUser.setId(comment.getUserId().toString());
                    return new CommentEntity(comment.getId().toString(), commentUser, comment.getContent());
                }
        ).forEach(commentEntities::add);

        commentEntities.forEach(commentEntity -> commentEntity.setPost(postEntity));
        postEntity.setComments(commentEntities);

        this.postJpaRepository.save(postEntity);
    }

    @Override
    public Optional<Post> findById(String id) {
        Optional<PostEntity> postEntity = this.postJpaRepository.findById(id);
        if (postEntity.isEmpty()) {
            return Optional.empty();
        }

        PostEntity post = postEntity.get();
        List<CommentEntity> comments = post.getComments();
        List<Comment> commentList = new ArrayList<>();
        comments.stream().map(
                commentEntity -> new Comment(
                        new Id(commentEntity.getId()),
                        commentEntity.getContent(),
                        new Id(commentEntity.getUser().getId())
                )
        ).forEach(commentList::add);

        return Optional.of(
                new Post(
                        new Id(post.getId()),
                        new Title(post.getTitle()),
                        new PostContent(post.getContent()),
                        new Id(post.getUser().getId()),
                        new Summary(post.getSummary()),
                        post.getThumbnail(),
                        new Slug(post.getSlug(), new Title(post.getTitle())),
                        commentList,
                        post.getPublishedAt(),
                        post.getVersion()
                )
        );
    }
}
