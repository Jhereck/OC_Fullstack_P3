package com.p3.chatop.chatop.web.controller;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.p3.chatop.chatop.web.model.User;
import com.p3.chatop.chatop.web.payload.request.LoginRequest;
import com.p3.chatop.chatop.web.payload.response.TokenResponse;
import com.p3.chatop.chatop.web.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@RequestMapping("/api")
@RestController
public class LoginController {

    @Autowired
    UserRepository usersRepository;

    // A secret key used to sign the JWT
    Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public static int USER_ID = 0;

    @PostMapping("auth/login")
    public Object login(@RequestBody LoginRequest loginRequest) {

        if (!isValidUser(loginRequest)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // Create a JWT with the user's information and a expiration time
        String jwt = Jwts.builder()
                .setSubject(loginRequest.getEmail())
                .setExpiration(new Date(System.currentTimeMillis() + 864000000)) // 10 days
                .signWith(KEY)
                .compact();
        return new TokenResponse(jwt);
    }

    @GetMapping("/auth/me")
    public ResponseEntity<?> protectedUrl(@RequestHeader("Authorization") String authorization) {
        // Extract the JWT from the "Authorization" header
        String jwt = authorization.substring(7);

        try {
            // Verify the JWT and extract the user's subject (username)
            String username = Jwts.parser()
                    .setSigningKey(KEY)
                    .parseClaimsJws(jwt)
                    .getBody()
                    .getSubject();

            // The JWT is valid, so allow the request

            User existingUserEmail = usersRepository.findUserByEmail(username);
            USER_ID = existingUserEmail.getId();
            return ResponseEntity.ok(existingUserEmail);
        } catch (Exception e) {
            // The JWT is invalid, so return an error
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private boolean isValidUser(LoginRequest loginRequest) {
        // Check if the user is valid by checking the database or other methods

        Optional<User> existingUserEmail = usersRepository.findByEmail(loginRequest.getEmail());

        if ((existingUserEmail.isPresent()) && (loginRequest.getPassword().equals(
                existingUserEmail.get().getPassword()))) {
            return true;
        } else {
            return false;
        }

    }
}