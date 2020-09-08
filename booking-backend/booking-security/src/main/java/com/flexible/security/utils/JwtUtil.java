package com.flexible.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Component
public class JwtUtil {
    private static long expirationTime;
    private static String secretKey;

    @Value("${jwt.token.expiration.in-ms}")
    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }
    @Value("${jwt.signing.key.secret}")
    public void setKey(String secretKey) { this.secretKey = secretKey; }


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException ex) {
            claims = ex.getClaims();
        }
        return claims;
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean isUsernameMatch(String token, String username) {
        String tokenUsername = extractUsername(token);
        return tokenUsername.equals(username);
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("admin", false);
        Optional<? extends GrantedAuthority> ga = userDetails.getAuthorities().stream().findFirst();
        if(ga.isPresent()) {
            if("ADMIN".equals(ga.get().getAuthority())) {
                claims.replace("admin", true);
            }
        }
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return (isUsernameMatch(token, userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String refreshToken(String token) {
        Claims claims = extractAllClaims(token);
        return createToken(claims, claims.getSubject());
    }
}
