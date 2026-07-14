package com.example.ai_chatbot_integration_platform.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ChannelDTO {

    private Long id;

    private Long botId;

    private String channelType;

    private String deploymentStatus;
    
}
