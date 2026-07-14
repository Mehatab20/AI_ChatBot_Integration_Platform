package com.example.ai_chatbot_integration_platform.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    private String userName;

    private String email;

    private String password;

    private String role;

    private Boolean isActive;

}