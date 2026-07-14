package com.example.ai_chatbot_integration_platform.service;

import java.util.List;

import com.example.ai_chatbot_integration_platform.DTO.UserRequest;
import com.example.ai_chatbot_integration_platform.DTO.UserResponse;

public interface UserService {

    UserResponse createUser(UserRequest userRequest);
    UserResponse getUserById(Long id);
    List<UserResponse> getAllUsers();
    UserResponse updateUser(Long id, UserRequest userRequest);
    void deleteUserById(Long id);
}
