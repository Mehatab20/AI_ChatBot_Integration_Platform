package com.example.ai_chatbot_integration_platform.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class IntentResponse {

    private Long id;

    private Long botId;

    private String name;

    private String trainingPhrases;

    private String responses;

    private Double confidence;

    private Boolean isActive;
    
}
