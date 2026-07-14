package com.example.ai_chatbot_integration_platform.service;

import java.util.List;

import com.example.ai_chatbot_integration_platform.DTO.ChatBotRequest;
import com.example.ai_chatbot_integration_platform.DTO.ChatBotResponse;

public interface ChatBotService {

    ChatBotResponse addChatBot(ChatBotRequest chatBotDTO);
    ChatBotResponse getChatBotById(Long id);
    List<ChatBotResponse> getChatBotsByOwnerId(Long ownerId);
    List<ChatBotResponse> getAllChatBots();
    List<ChatBotResponse> getIntegrationStatus(String platform);
    ChatBotResponse updateChatBot(Long id,ChatBotRequest chatBotDTO);
    void deleteChatBot(Long id);
}
