package com.example.ai_chatbot_integration_platform.DTO;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ConversationResponse {

    
    private Long id;

    private Long botId;

    private Long userId;

    private LocalDateTime startTime;

    private String status;
    
}
