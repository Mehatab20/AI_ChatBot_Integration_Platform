package com.example.ai_chatbot_integration_platform.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ai_chatbot_integration_platform.DTO.LoginRequest;
import com.example.ai_chatbot_integration_platform.DTO.LoginResponse;
import com.example.ai_chatbot_integration_platform.DTO.RegisterRequest;
import com.example.ai_chatbot_integration_platform.entity.User;
import com.example.ai_chatbot_integration_platform.repository.UserRepository;
import com.example.ai_chatbot_integration_platform.security.JwtUtil;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil,
            AuthenticationManager authenticationManager) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String register(RegisterRequest request) {

        User user = new User();

        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());

        user.setPasswordHash(
                passwordEncoder.encode(request.getPassword()));

        user.setRole(request.getRole());

        user.setIsActive(request.getIsActive());

        userRepository.save(user);

        return "User Registered Successfully";

    }

    @Override
    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(

                        request.getEmail(),
                        request.getPassword()));

        String token = jwtUtil.generateToken(request.getEmail());

        return new LoginResponse(token);

    }

}