package com.example.security_jwt.service;

import com.example.security_jwt.entity.User;

public interface JwtService {
    String generateToken(User user);

    String extractUsername(String token);
}

