package com.ems.utill;

import org.springframework.stereotype.Component;

import com.ems.exceptionhandler.CustomExpiredJwtException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private String SECRET_KEY = "TaK+HaV^uvCHEFsEVfypW#7g9^k*Z8$V";

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String extractUsername(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    private Claims extractAllClaims(String token)throws CustomExpiredJwtException {
    	try {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    	}catch(ExpiredJwtException ex) {
    		String expirationTime = ex.getClaims().getExpiration().toString();
            String customMessage = "Your session has expired. Please log in again. Token expired at: " + expirationTime;
            System.out.println(customMessage); // Display to the console

            // Optionally, throw a new exception with a custom message
            throw new CustomExpiredJwtException(customMessage);

    	}catch (Exception ex) {
            System.out.println("Invalid token: " + ex.getMessage());
            throw new RuntimeException("Invalid JWT Token");
        }
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .header().empty().add("typ","JWT")
                .and()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 50)) // 5 minutes expiration time
                .signWith(getSigningKey())
                .compact();
    }

    public Boolean validateToken(String token) throws ExpiredJwtException{
       try {
    	   System.out.println("Validating...");
    	return !isTokenExpired(token);
       }catch(ExpiredJwtException ex) {
    	   throw new ExpiredJwtException(null, null, ex.getMessage());
       }
       }
}