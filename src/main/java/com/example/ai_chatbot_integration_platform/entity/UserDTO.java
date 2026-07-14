package com.example.ai_chatbot_integration_platform.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long id;

    private String userName;

    private String email;

    private String role;

    private Boolean isActive;


}
