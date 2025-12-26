package com.example.demo.service;

import com.example.demo.dto.*;

public interface AuthService {
    AuthResponse login(AuthRequest request);
}
