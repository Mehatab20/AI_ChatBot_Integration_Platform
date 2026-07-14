package com.example.ai_chatbot_integration_platform.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MessageRequest {

    private Long conversationId;

    private String senderType;

    private String content;
    
}
