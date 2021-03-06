package dev.piatnitsa.animallibrary.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * This class provides tools for working with JSON Web Token.
 * @author Vlad Piatnitsa
 * @version 1.0
 */
@Component
public class TokenProvider {
    @Value("${token.secret}") private String jwtSecret;
    @Value("${token.issuer}") private String issuer;
    @Value("${token.expiration}") private long expirationInMinutes;
    private static final String EMAIL_CLAIM = "email";

    public String generateToken(String email) {
        Date expiresAt = Date.from(LocalDateTime.now()
                .plusMinutes(expirationInMinutes)
                .atZone(ZoneId.systemDefault())
                .toInstant());
        return JWT.create()
                .withClaim(EMAIL_CLAIM, email)
                .withExpiresAt(expiresAt)
                .withIssuer(issuer)
                .sign(Algorithm.HMAC512(jwtSecret));
    }

    public boolean validateToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC512(jwtSecret))
                    .withIssuer(issuer)
                    .build();
            verifier.verify(token);
        } catch (JWTVerificationException ex) {
            return false;
        }
        return true;
    }

    public String getLoginFromToken(String token) {
        return JWT.decode(token)
                .getClaim(EMAIL_CLAIM)
                .asString();
    }
}
