package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AuthServiceImpl implements AuthService {

    public AuthServiceImpl(UserAccountRepository repo,
                           BCryptPasswordEncoder encoder,
                           JwtTokenProvider tokenProvider) {
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        return new AuthResponse("dummy-token");
    }
}
