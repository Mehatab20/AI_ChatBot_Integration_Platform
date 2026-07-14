package com.example.ai_chatbot_integration_platform.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class IntentRequest {

    private Long botId;

    private String name;

    private String trainingPhrases;

    private String responses;

    private Double confidence;

}
