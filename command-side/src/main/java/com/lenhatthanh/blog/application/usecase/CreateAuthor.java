package com.lenhatthanh.blog.application.usecase;

import com.lenhatthanh.blog.domain.Author;
import com.lenhatthanh.blog.domain.repository.AuthorRepositoryInterface;
import com.lenhatthanh.blog.dto.AuthorDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
@AllArgsConstructor
public class CreateAuthor {
    private AuthorRepositoryInterface authorRepository;

    public void execute(AuthorDto authorRequest) {
        Author author = new Author(
                UUID.randomUUID().toString(),
                authorRequest.getName(),
                authorRequest.getEmail()
        );

        authorRepository.save(author);
    }
}
