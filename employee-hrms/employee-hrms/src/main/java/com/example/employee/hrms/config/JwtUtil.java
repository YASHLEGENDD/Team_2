//package com.example.employee.hrms.config;
//
//import java.nio.charset.StandardCharsets;
//import java.security.Key;
//import java.util.Date;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//
//@Component
//public class JwtUtil {
//
//    private static final String SECRET_KEY =
//            "placement_platform_secret_key_2026_secure_123456";
//
//    private static final long EXPIRATION_TIME =
//            1000 * 60 * 60 * 5; // 5 hours
//
//    private Key getSignKey() {
//        return Keys.hmacShaKeyFor(
//                SECRET_KEY.getBytes(StandardCharsets.UTF_8)
//        );
//    }
//
//    // ===================== GENERATE TOKEN =====================
//    public String generateToken(String username, String role) {
//
//        return Jwts.builder()
//                .setSubject(username)
//                .claim("role", role)
//                .setIssuedAt(new Date())
//                .setExpiration(
//                        new Date(System.currentTimeMillis() + EXPIRATION_TIME)
//                )
//                .signWith(getSignKey())
//                .compact();
//    }
//
//    // ===================== EXTRACT USERNAME =====================
//    public String extractUsername(String token) {
//        return getClaims(token).getSubject();
//    }
//
//    // ===================== VALIDATE TOKEN =====================
//    public boolean validateToken(String token, UserDetails userDetails) {
//
//        final String username = extractUsername(token);
//
//        return username.equals(userDetails.getUsername())
//                && !isTokenExpired(token);
//    }
//
//    // ===================== CHECK EXPIRY =====================
//    private boolean isTokenExpired(String token) {
//        return getClaims(token)
//                .getExpiration()
//                .before(new Date());
//    }
//
//    // ===================== PARSE CLAIMS =====================
//    private Claims getClaims(String token) {
//
//        return Jwts.parserBuilder()
//                .setSigningKey(getSignKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//}

package com.example.employee.hrms.config;

import java.security.Key;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private final String SECRET_KEY =
            "mysecretkeymysecretkeymysecretkeymysecretkey"; // 32+ chars

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // ===================== GENERATE TOKEN =====================
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 5)
                )
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ===================== EXTRACT USERNAME =====================
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // ===================== VALIDATE TOKEN =====================
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}