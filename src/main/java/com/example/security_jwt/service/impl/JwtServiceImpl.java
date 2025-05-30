package com.example.security_jwt.service.impl;

import com.example.security_jwt.entity.User;
import com.example.security_jwt.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
    private static final String SECRET_KEY =
            "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    @Override
    public String generateToken(User user) {
        return Jwts
                .builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignedKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String extractUsername(String token) {
        Claims claims = extractClaims(token);
        if (claims != null) {
            // check han token
            Date expirationTime = claims.getExpiration();
            boolean isExpired = expirationTime.before(Date.from(Instant.now()));
            if (!isExpired) {
                return claims.getSubject();
            } else return null;
        }
        return null;
    }


    // claims o trong phan payload,chua thong tin nguoi dung
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignedKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignedKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
