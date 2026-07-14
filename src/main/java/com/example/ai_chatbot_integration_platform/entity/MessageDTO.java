package com.example.ai_chatbot_integration_platform.entity;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MessageDTO {

    private Long id;

    private Long conversationId;

    private String senderType;

    private String content;

    private LocalDateTime timestamp;
    
}
