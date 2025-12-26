package com.example.demo.dto;

public class AuthResponseDto {

    private String token;

    // ✅ Constructor name MUST match class name
    public AuthResponseDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
