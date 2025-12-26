package com.example.demo.security;

import com.example.demo.model.UserAccount;
// The import for Jwts is correct. No need to change.
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    // Field name must be 'jwtSecret' for TestNG setup
    @Value("${app.jwtSecret:default-test-secret-change-this-secret-key-change}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs:3600000}")
    private int jwtExpirationInMs;
    
    private Key key;

    // Use PostConstruct to initialize the Key after the @Value injection
    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateToken(UserAccount user) {
        // ... (This section is correct)
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole());
        claims.put("userId", user.getId());

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .addClaims(claims)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    // FIX: Replaced Jwts.parserBuilder() with Jwts.parser()
    public String getEmail(String token) {
        Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    // FIX: Replaced Jwts.parserBuilder() with Jwts.parser()
    public String getRole(String token) {
        Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return (String) claims.get("role");
    }

    // FIX: Replaced Jwts.parserBuilder() with Jwts.parser()
    public Long getUserId(String token) {
        Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody();
        Object userId = claims.get("userId");
        if (userId instanceof Integer) {
            return ((Integer) userId).longValue();
        }
        return (Long) userId;
    }

    // FIX: Replaced Jwts.parserBuilder() with Jwts.parser()
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(key).build().parseClaimsJws(authToken);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}