
package com.ifgram.api.controller;

import com.ifgram.api.dto.AuthDtos.*;
import com.ifgram.api.entity.User;
import com.ifgram.api.security.TokenService;
import com.ifgram.api.service.UserService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService users;
    private final TokenService tokens;

    public AuthController(UserService users, TokenService tokens){
        this.users = users; this.tokens = tokens;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest req){
        try{
            User u = users.signup(req.email, req.password, req.telefone);
            String token = tokens.issue(u.getId());
            Map<String, Object> body = new HashMap<>();
            body.put("userId", u.getId());
            body.put("email", u.getEmail());
            body.put("telefone", u.getTelefone());
            body.put("createdAt", DateTimeFormatter.ISO_INSTANT.format(u.getCreatedAt()));
            body.put("token", token);
            return ResponseEntity.status(HttpStatus.CREATED).body(body);
        }catch(IllegalStateException dup){
            return ResponseEntity.status(409).body(Map.of("error","email já cadastrado"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req){
        var u = users.validatePassword(req.email, req.password);
        if (u.isEmpty()){
            return ResponseEntity.status(401).body(Map.of("error","credenciais inválidas"));
        }
        String token = tokens.issue(u.get().getId());
        return ResponseEntity.ok(new TokenResponse(token, tokens.getTtlSeconds()));
    }
}
