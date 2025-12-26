package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.exception.BadRequestException;
import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.AuthService;

public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository userRepo;

    public AuthServiceImpl(UserAccountRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {

        UserAccount user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid credentials"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new BadRequestException("Invalid credentials");
        }

        return new AuthResponse(
                "dummy-jwt-token",
                user.getRole(),
                user.getEmail()
        );
    }
}
