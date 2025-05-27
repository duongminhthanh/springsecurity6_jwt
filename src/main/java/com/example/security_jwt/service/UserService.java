package com.example.security_jwt.service;

import com.example.security_jwt.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    List<User> getUsers();

    User getUserById(Long userId);

    User updateUser(Long userId, User user);
}

