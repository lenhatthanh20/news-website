package com.lenhatthanh.blog.service;

import com.lenhatthanh.blog.exception.AuthorNotFoundException;
import com.lenhatthanh.blog.model.Author;
import com.lenhatthanh.blog.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorService {
    AuthorRepository authorRepository;

    public List<AuthorDto> getAll() {
        List<Author> authors = (List<Author>) authorRepository.findAll();

        return authors.stream()
                .map(author -> new AuthorDto(author.getAuthorId(), author.getName(), author.getEmail()))
                .toList();
    }

    public AuthorDto findByEmail(String id) {
        Optional<Author> author = authorRepository.findOneByEmail(id);
        if (author.isEmpty()) {
            throw new AuthorNotFoundException("The author is not found");
        }

        return new AuthorDto(author.get().getAuthorId(), author.get().getName(), author.get().getEmail());
    }
}
