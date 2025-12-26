package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    // ✅ MUST be SecretKey (not Key)
    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private final long jwtExpirationMs = 24 * 60 * 60 * 1000; // 1 day

    // ✅ Generate Token
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key)
                .compact();
    }

    // ✅ Validate Token (JJWT 0.12.x)
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key)   // ✅ now matches SecretKey
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ Extract Username
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }
}
