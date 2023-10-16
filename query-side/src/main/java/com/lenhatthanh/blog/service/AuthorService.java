package com.lenhatthanh.blog.service;

import com.lenhatthanh.blog.model.Author;
import com.lenhatthanh.blog.repository.AuthorRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorService {
    AuthorRepositoryInterface authorRepository;

    public List<AuthorDto> getAll() {
        List<Author> authors = (List<Author>) authorRepository.findAll();

        return authors.stream()
                .map(author -> new AuthorDto(author.getAuthorId(), author.getName(), author.getEmail()))
                .toList();
    }
}
