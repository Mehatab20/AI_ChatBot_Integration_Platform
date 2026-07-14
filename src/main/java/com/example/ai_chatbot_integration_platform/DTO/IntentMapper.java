package com.example.ai_chatbot_integration_platform.DTO;

import org.springframework.stereotype.Component;

import com.example.ai_chatbot_integration_platform.entity.Intent;

@Component
public class IntentMapper {

    public Intent toEntity(IntentRequest intentRequest){
        Intent intent=new Intent();

        intent.setName(intentRequest.getName());
        intent.setTrainingPhrases(intentRequest.getTrainingPhrases());
        intent.setResponses(intentRequest.getResponses());
        intent.setConfidence(intentRequest.getConfidence());
        intent.setIsActive(true);

        return intent;

    }

    public IntentResponse toResponse(Intent intent){
        IntentResponse intentResponse=new IntentResponse();

        intentResponse.setId(intent.getId());
        intentResponse.setName(intent.getName());
        intentResponse.setTrainingPhrases(intent.getTrainingPhrases());
        intentResponse.setResponses(intent.getResponses());
        intentResponse.setConfidence(intent.getConfidence());

        if(intent.getBot()!=null){
            intentResponse.setBotId(intent.getBot().getId());
        }

        return intentResponse;
    }

}
