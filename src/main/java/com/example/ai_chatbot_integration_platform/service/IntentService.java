package com.example.ai_chatbot_integration_platform.service;

import java.util.List;

import com.example.ai_chatbot_integration_platform.DTO.IntentRequest;
import com.example.ai_chatbot_integration_platform.DTO.IntentResponse;

public interface IntentService {

    IntentResponse addIntent(IntentRequest intentRequest);
    IntentResponse getIntentById(Long id);
    List<IntentResponse> getIntentsByBotId(Long botId);
    IntentResponse updateIntent(Long id, IntentRequest intentRequest);
    void deleteIntentById(Long id);

}
