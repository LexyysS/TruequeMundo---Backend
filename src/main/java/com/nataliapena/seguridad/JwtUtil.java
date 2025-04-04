package com.nataliapena.seguridad;


import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.expiration}")
    private long EXPIRATION_TIME;

    public String generateToken(Long userId, String email) {
        return Jwts.builder()
        		.setSubject(userId.toString()) 
        		.claim("email", email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            throw new RuntimeException("Firma JWT inválida");
        } catch (MalformedJwtException e) {
            throw new RuntimeException("Token JWT inválido");
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Token JWT expirado");
        } catch (UnsupportedJwtException e) {
            throw new RuntimeException("Token JWT no soportado");
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("JWT claims string está vacío");
        }
    }
}
