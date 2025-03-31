package com.example.demo;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWT_UTILS {
      private final Key key;
      private final long jwtExpirationInMs=3600000;
      JWT_UTILS(@Value("${jwt.secret}") String secret) {
          this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
      }
      public String generalToken(String username) {
    	  return Jwts.builder()
    			  .setSubject(username)
    			  .setIssuedAt(new Date())
                  .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                  .signWith(key)
                  .compact();
    			  
      }
      public String getUsernameFromToken(String token) {
          try {
              Claims claims = Jwts.parserBuilder()
                      .setSigningKey(key)
                      .build()
                      .parseClaimsJws(token)
                      .getBody();
              return claims.getSubject();
          } catch (JwtException | IllegalArgumentException e) {
              System.err.println("Недействительный токен: " + e.getMessage());
              return null;
          }
      }

      
      public boolean validateToken(String token) {
          try {
              Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
              return true;
          } catch (JwtException | IllegalArgumentException e) {
              return false;
          }
      }
  }

