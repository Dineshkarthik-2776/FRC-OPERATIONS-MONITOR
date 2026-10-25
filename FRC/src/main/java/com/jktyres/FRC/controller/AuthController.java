package com.jktyres.FRC.controller;

import com.jktyres.FRC.dto.LoginRequest;
import com.jktyres.FRC.dto.RegisterRequest;
import com.jktyres.FRC.model.UserEntity;
import com.jktyres.FRC.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(@RequestBody RegisterRequest registerRequest){
        UserEntity user = authService.registerUser(registerRequest);
        return ResponseEntity.status(201).body(user);
    }

    @PostMapping("/login")
    public String authenticate(@RequestBody LoginRequest loginRequest){
        return authService.authenticate(loginRequest);
    }



}
