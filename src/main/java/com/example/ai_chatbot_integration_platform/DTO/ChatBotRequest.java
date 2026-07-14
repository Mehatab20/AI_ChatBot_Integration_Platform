package com.example.ai_chatbot_integration_platform.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ChatBotRequest {

    private Long ownerId;

    private String botName;

    private String platform;

    private String response;
    

}
