package com.jktyres.FRC.service;

import com.jktyres.FRC.Repository.UserRepository;

import com.jktyres.FRC.auth.JwtService;
import com.jktyres.FRC.dto.LoginRequest;
import com.jktyres.FRC.dto.RegisterRequest;
import com.jktyres.FRC.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;

    public UserEntity registerUser(RegisterRequest registerRequest){
        UserEntity user = UserEntity.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .role(registerRequest.getRole())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .isActive(true)
                .lastLogin(LocalDateTime.now())
                .build();
        return userRepository.save(user);
    }

    public String authenticate(LoginRequest credentials){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getEmail(),
                        credentials.getPassword()));

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(credentials.getEmail());
        }

        return "Invalid";
    }
}
