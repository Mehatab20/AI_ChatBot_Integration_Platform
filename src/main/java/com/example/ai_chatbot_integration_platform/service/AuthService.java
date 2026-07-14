package com.example.ai_chatbot_integration_platform.service;

import com.example.ai_chatbot_integration_platform.DTO.LoginRequest;
import com.example.ai_chatbot_integration_platform.DTO.LoginResponse;
import com.example.ai_chatbot_integration_platform.DTO.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

}