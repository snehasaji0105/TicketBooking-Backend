package com.example.ticket.booking.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String JwtSecret;
    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication) {
        String userName=authentication.getName();
        Date currentDate=new Date();
        Date expireDate=new Date(currentDate.getTime() + jwtExpirationInMs);
        String token= Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,JwtSecret).compact();
        return token;
    }
    public String getUsernameFromJWT(String token){
        Claims claims=Jwts.parser().setSigningKey(JwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(JwtSecret)
                    .parseClaimsJws(token);
            return true;
        }catch(SignatureException e){
            throw new SignatureException(e.getMessage(), e);
        }
    }
}
