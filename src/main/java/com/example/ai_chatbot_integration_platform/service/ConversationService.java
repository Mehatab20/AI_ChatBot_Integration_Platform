package com.example.ai_chatbot_integration_platform.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.ai_chatbot_integration_platform.DTO.ConversationRequest;
import com.example.ai_chatbot_integration_platform.DTO.ConversationResponse;

public interface ConversationService {

    ConversationResponse startConversation(@RequestBody ConversationRequest conversationRequest);
    ConversationResponse getConversationById(@PathVariable Long id);
    List<ConversationResponse> getAllConversationsByBotId(@PathVariable Long botId);
    List<ConversationResponse> getAllConversationsByUserId(@PathVariable Long userId);
    ConversationResponse updateConversation(@PathVariable Long id,@RequestBody ConversationRequest conversationRequest);
    void deleteConversationById(@PathVariable Long id);
}
