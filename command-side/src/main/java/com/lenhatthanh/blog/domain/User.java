package com.lenhatthanh.blog.domain;

import com.lenhatthanh.blog.domain.exception.InvalidUserNameException;
import com.lenhatthanh.blog.domain.exception.NotFoundEmailException;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
public class User extends Entity<String> {
    public int MAX_NAME_LENGTH = 50;
    public int MIN_NAME_LENGTH = 3;

    private String name;
    private String email;
    private final String password;
    private final List<Role> roles = new ArrayList<>();

    public User(String id, String name, String email, String password) {
        super(id);
        this.setName(name);
        this.setEmail(email);
        this.password = password;
    }

    public void setName(String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new InvalidUserNameException("DOMAIN-ERROR-0003");
        }

        this.name = name;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new NotFoundEmailException("DOMAIN-ERROR-0004");
        }

        this.email = email;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }
}
