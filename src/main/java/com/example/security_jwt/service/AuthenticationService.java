package com.example.security_jwt.service;

import com.example.security_jwt.model.AuthenticationRequest;
import com.example.security_jwt.model.AuthenticationResponse;
import com.example.security_jwt.model.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse login(AuthenticationRequest request); // login method
}
