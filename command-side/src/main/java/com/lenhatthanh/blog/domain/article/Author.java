package com.lenhatthanh.blog.domain.article;

import com.lenhatthanh.blog.domain.Entity;
import com.lenhatthanh.blog.domain.exception.InvalidAuthorNameException;
import com.lenhatthanh.blog.domain.exception.NotFoundEmailException;
import lombok.*;

@Getter
public class Author extends Entity<String> {
    public int MAX_NAME_LENGTH = 50;
    public int MIN_NAME_LENGTH = 3;

    private String name;
    private String email;

    public Author(String id, String name, String email) {
        super(id);
        this.setName(name);
        this.setEmail(email);
    }

    public void setName(String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new InvalidAuthorNameException("DOMAIN-ERROR-0003");
        }

        this.name = name;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new NotFoundEmailException("DOMAIN-ERROR-0004");
        }

        this.email = email;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + this.getId() + '\'' +
                ", name='" + this.name + '\'' +
                ", email='" + this.email + '\'' +
                '}';
    }
}
