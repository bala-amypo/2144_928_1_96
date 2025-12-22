package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public AuthResponse login(AuthRequest request) {

        // SIMPLE DUMMY LOGIN (NO SECURITY)
        if ("admin@gmail.com".equals(request.getEmail()) &&
            "admin123".equals(request.getPassword())) {

            return new AuthResponse(
                "LOGIN_SUCCESS",
                1L,
                request.getEmail(),
                "ADMIN"
            );
        }

        throw new RuntimeException("Invalid email or password");
    }
}
