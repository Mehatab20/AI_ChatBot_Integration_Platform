package com.example.ai_chatbot_integration_platform.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ChannelResponse {

    private Long id;

    private Long botId;

    private String channelType;

    private String deploymentStatus;
    
}
