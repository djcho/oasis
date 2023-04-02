package com.oasis.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class InvitationTokenProvider {
    @Value("${spring.jwt.invitation.secret.key}")
    private String secretKey = "invTokenSecretKey";

    public String genToken(Date expiryDate) {

        UUID uuid = UUID.randomUUID();
        String token = Jwts.builder()
                .setId(uuid.toString())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

        return token;
    }

    public boolean verifyToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);

            Date expiryDate = claims.getBody().getExpiration();
            if (expiryDate.before(new Date())) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
