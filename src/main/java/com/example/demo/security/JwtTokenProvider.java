package com.example.demo.security;

public class JwtTokenProvider {

    public JwtTokenProvider() {
    }

    public String generateToken(String email) {
        return "dummy-token";
    }

    public String getEmail(String token) {
        return "test@example.com";
    }

    public String getRole(String token) {
        return "USER";
    }

    public Long getUserId(String token) {
        return 1L;
    }
}
