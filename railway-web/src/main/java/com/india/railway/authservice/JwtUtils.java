package com.india.railway.authservice;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.india.railway.model.mysql.Role;

import java.security.Key;

@Component
public class JwtUtils {

    // private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); //
    // Replace with a stronger key in
    // production

    private static final String SECRET_KEY = "your-secure-secret-key-32-characters-minimum";

    private static Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String username, List<Role> roles) {

        List<String> roleslist = roles.stream().map(Role::getRolename).collect(Collectors.toList());
        return Jwts.builder()
                .setSubject(username)
                // .claim("role", roleslist)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour expiry
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsernameFromToken(String token) {

        String username = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token) // Parse the token
                .getBody() // Get the claims
                .getSubject();

        return username;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);

            System.out.println("Token is valid.");

        } catch (JwtException e) {
            System.out.println("Invalid token: " + e.getMessage());
            return false;
        }
        return true;

    }

    // private static final String key = "your-secure-secret-key";

    // Generate a JWT token
    /*
     * public String generateToken(String username, List<Role> roles) {
     * 
     * System.out.println(key);
     * List<String> roleslist = roles.stream()
     * .map(Role::getRolename)
     * .collect(Collectors.toList());
     * 
     * return Jwts.builder()
     * .setSubject(username)
     * // .claim("roles", roleslist)
     * .setIssuedAt(new Date())
     * .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
     * expiration
     * .signWith(key)
     * .compact();
     * }
     * 
     * // Extract username from the token
     * public String getUsernameFromToken(String token) {
     * Claims claims =
     * Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody
     * ();
     * return claims.getSubject();
     * }
     */

    // Validate the token

}
