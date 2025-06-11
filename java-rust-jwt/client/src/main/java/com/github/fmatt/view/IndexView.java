package com.github.fmatt.view;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;

@Named
@ViewScoped
public class IndexView implements Serializable {

    private String message = "Hello world!";

    private String jwt;

    @PostConstruct
    public void init() {
        System.out.println("Creating JWT token...");
        createToken();
        System.out.println("Token created: " + jwt);
    }

    private void createToken() {
        try {
            String jwtSecret = "123456";

            Random random = new Random();
            StringBuilder jwtId = new StringBuilder();
            for (int i = 0; i < 32; ++i)
                jwtId.append(Integer.toHexString(random.nextInt(15)));
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            jwt = JWT.create()
                    .withExpiresAt(LocalDateTime.now().plusMinutes(5).atZone(ZoneId.systemDefault()).toInstant())
                    .withIssuedAt(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())
                    .withNotBefore(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())
                    .withJWTId(jwtId.toString())
                    .withClaim("sub", "user")
                    .withClaim("type", "access")
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
