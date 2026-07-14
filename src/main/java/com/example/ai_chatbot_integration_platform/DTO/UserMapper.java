package com.example.ai_chatbot_integration_platform.DTO;

import com.example.ai_chatbot_integration_platform.entity.User;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequest userRequest){
        User user=new User();

        user.setUserName(userRequest.getUserName());
        user.setEmail(userRequest.getEmail());
        user.setPasswordHash(userRequest.getPassword());
        user.setRole(userRequest.getRole());
        user.setIsActive(userRequest.getIsActive());

        return user;
    }

    
    public UserResponse toResponse(User user){
        UserResponse userResponse=new UserResponse();

        userResponse.setUserName(user.getUserName());
        userResponse.setEmail(user.getEmail());
        userResponse.setId(user.getId());
        userResponse.setRole(user.getRole());
        userResponse.setIsActive(user.getIsActive());

        return userResponse;
    }

    
}
