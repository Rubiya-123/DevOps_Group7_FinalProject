package taskreminder.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;

@Component
public class JwtUtil {

  /*  @Value("${jwt.secret}")
    private String SECRET_KEY;*/
    
   

	private final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();

    private SecretKey getSigningKey() {
        return SECRET_KEY;
    }

    // =========================
    // EXTRACT CLAIMS
    // =========================
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())   // modern jjwt 0.12+
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // =========================
    // TOKEN VALIDATION
    // =========================
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    public String validateAccessToken(String token) {
        try {
            Claims claims = extractAllClaims(token);

            if (claims.getExpiration().before(new Date())) {
                throw new RuntimeException("Token expired");
            }

            return claims.getSubject();

        } catch (Exception e) {
            throw new RuntimeException("Invalid token: " + e.getMessage());
        }
    }

    // =========================
    // TOKEN GENERATION
    // =========================
    public String generateToken(String username) {

        Instant now = Instant.now();

        return Jwts.builder()
                .subject(username)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(60 * 60))) // 1 hour
                .signWith(getSigningKey())
                .compact();
    }
}