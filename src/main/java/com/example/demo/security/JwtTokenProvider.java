package com.example.demo.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.demo.model.UserAccount;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

    // MUST exist (tests / reflection / config)
    private String jwtSecret = "defaultSecretKeyMustBeAtLeast32CharsLong";

    private final long jwtExpirationMs = 86400000;

    public JwtTokenProvider() {
    }

    // 🔑 Signing Key (JJWT 0.12.x)
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    // ✅ Generate JWT
    public String generateToken(UserAccount user) {
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("userId", user.getId())
                .claim("role", user.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey())
                .compact();
    }

    // ✅ Validate JWT
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ Extract Email
    public String getEmail(String token) {
        return getClaims(token).getSubject();
    }

    // ✅ Extract Role
    public String getRole(String token) {
        return (String) getClaims(token).get("role");
    }

    // ✅ Extract User ID
    public Long getUserId(String token) {
        Object id = getClaims(token).get("userId");
        return id == null ? null : Long.valueOf(id.toString());
    }

    // 🔍 Internal Claims Parser
    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
