package com.example.ai_chatbot_integration_platform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ai_chatbot_integration_platform.DTO.LoginRequest;
import com.example.ai_chatbot_integration_platform.DTO.LoginResponse;
import com.example.ai_chatbot_integration_platform.DTO.RegisterRequest;
import com.example.ai_chatbot_integration_platform.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {

        this.authService = authService;

    }

    @PostMapping("/register")
    public ResponseEntity<String> register(

            @RequestBody RegisterRequest request) {

        return new ResponseEntity<>(

                authService.register(request),

                HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(

            @RequestBody LoginRequest request) {

        return ResponseEntity.ok(

                authService.login(request));

    }

}