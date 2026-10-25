package com.jktyres.FRC.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {

    public static final String SECERET = "MySecretKey123456789012345678901234567890";

    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*30))
                .addClaims(new HashMap<>())
                .signWith(getSignedKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignedKey(){
        return Keys.hmacShaKeyFor(SECERET.getBytes());
    }

    public Claims verifySignatureAndExtractClaims(String token){
        return Jwts.parser()
                .setSigningKey(getSignedKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractEmail(String token){
        return verifySignatureAndExtractClaims(token).getSubject();
    }

    public Date getExpiration(String token){
        return verifySignatureAndExtractClaims(token).getExpiration();
    }

    public boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }
}
