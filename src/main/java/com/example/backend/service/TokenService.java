package com.example.backend.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.backend.Entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class TokenService {

    @Value("${app.token.secret}")
    private String secret;
    @Value("${app.token.issuer}")
    private String issuer;
    private Algorithm algorithm() {
        return Algorithm.HMAC256(secret);
    }
    public String tokenize(User user) {

        Calendar calenda = Calendar.getInstance();
        calenda.add(Calendar.MINUTE,60);
        Date exiresAt = calenda.getTime();


        Algorithm algorithm = algorithm();
        String token = JWT.create()
                .withIssuer(issuer)
                .withClaim("principal", user.getId())
                .withClaim("role", "USER")
                .withExpiresAt(exiresAt)
                .sign(algorithm);

        return token;
    }

    public DecodedJWT verify(String token){
        try {
            JWTVerifier verifier = JWT.require(algorithm())
                    // specify an specific claim validations
                    .withIssuer(issuer)
                    // reusable verifier instance
                    .build();

            return verifier.verify(token);

        } catch (JWTVerificationException exception){
            return null;
        }
    }
}
