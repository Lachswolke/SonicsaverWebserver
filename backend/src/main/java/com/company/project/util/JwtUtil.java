package com.company.project.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512); // Secure 512-bit key
    private final long EXPIRATION_TIME = 86400000; // 24 hours in milliseconds

    // Generate JWT token using the Authentication object
    public String generateToken(Authentication authentication) {
        String username = authentication.getName(); // Extract the username from the Authentication object
        return Jwts.builder()
                .setSubject(username) // Set the username as the subject of the token
                .setIssuedAt(new Date()) // Set the issue date
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Set expiration date
                .signWith(SECRET_KEY) // Sign the token with the secret key
                .compact(); // Build the compact JWT token string
    }

    // Validate the token by checking its integrity and expiration
    public boolean validateToken(String token) {
        try {
            // Parse and validate the JWT token using the secret key
            Jwts.parserBuilder()  // Use parserBuilder() for more secure parsing
                    .setSigningKey(SECRET_KEY) // Set the secret key for validation
                    .build()  // Build the parser
                    .parseClaimsJws(token);  // Parse and validate the token
            return true; // Token is valid
        } catch (JwtException | IllegalArgumentException e) {
            // If parsing or validation fails, return false
            return false;
        }
    }

    // Extract the username (subject) from the token
    public String extractUsername(String token) {
        // Parse the JWT and retrieve the username (subject)
        return Jwts.parserBuilder()  // Use parserBuilder() for more secure parsing
                .setSigningKey(SECRET_KEY) // Set the secret key for validation
                .build()  // Build the parser
                .parseClaimsJws(token)  // Parse the token and retrieve claims
                .getBody()  // Get the body (claims) of the JWT
                .getSubject(); // Extract the subject (username)
    }
}
