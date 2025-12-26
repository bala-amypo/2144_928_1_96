package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;

public interface AuthService {
    AuthResponse authenticate(AuthRequest req);
    AuthResponse register(AuthRequest req); // <--- THIS MUST BE PRESENT
}