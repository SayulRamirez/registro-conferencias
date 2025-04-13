package com.registroconferencias.jwt;

import com.registroconferencias.model.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtService {

    @Value(value = "${KEY}")
    private String SECRET_KEY;


    public String getToken(UserEntity user) {
        return getToken(new HashMap<>(), user);
    }

    private String getToken(HashMap<String,Object> extractClaims, UserEntity user) {
        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setId(user.getId().toString())
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*24))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getEmailFromToken(String token) {
        System.out.println(getClaim(token, Claims::getSubject));
        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {

        final String email = getEmailFromToken(token);

        System.out.println(email);
        System.out.println("Token: " + isTokenExpired(token));
        return email.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);

        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token) {
        System.out.println(getClaim(token, Claims::getExpiration).toString());
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }
}
