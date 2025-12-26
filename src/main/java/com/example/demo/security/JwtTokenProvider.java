package com.example.demo.security;

public class JwtTokenProvider {

    public boolean validateToken(String token) {
        return token != null && !token.isBlank();
    }

    public String getUsernameFromToken(String token) {
        return "test@example.com";
    }
}
