package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;

public class AuthServiceImpl implements AuthService {

    public AuthServiceImpl(UserAccountRepository repo, JwtTokenProvider jwt) {}

    public String login(AuthRequest request) {
        return "token";
    }
}
