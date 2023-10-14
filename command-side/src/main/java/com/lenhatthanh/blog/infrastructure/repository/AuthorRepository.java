package com.lenhatthanh.blog.infrastructure.repository;

import com.lenhatthanh.blog.domain.article.Author;
import com.lenhatthanh.blog.domain.repository.AuthorRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class AuthorRepository implements AuthorRepositoryInterface {
    private AuthorJpaRepository authorJpaRepository;
    @Override
    public void save(Author author) {
        AuthorEntity authorEntity = AuthorEntity.builder()
                .id(author.getId())
                .name(author.getName())
                .email(author.getEmail())
                .build();

        this.authorJpaRepository.save(authorEntity);
    }

    @Override
    public Optional<Author> findById(String id) {
        Optional<AuthorEntity> authorEntity = this.authorJpaRepository.findById(id);
        if (authorEntity.isEmpty()) {
            return Optional.empty();
        }

        Author author = new Author(authorEntity.get().getId(), authorEntity.get().getName(), authorEntity.get().getEmail());

        return Optional.of(author);
    }
}
