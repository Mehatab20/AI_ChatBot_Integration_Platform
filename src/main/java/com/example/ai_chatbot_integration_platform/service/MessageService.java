package com.example.ai_chatbot_integration_platform.service;

import java.util.List;

import com.example.ai_chatbot_integration_platform.DTO.MessageRequest;
import com.example.ai_chatbot_integration_platform.DTO.MessageResponse;

public interface MessageService {

    MessageResponse sendMessage(Long conversationId, MessageRequest messageRequest);

    List<MessageResponse> getHistoryByConversationId(Long conversationId);

    //CRUD
    MessageResponse getMessageById(Long id);
    MessageResponse updateMessage(Long id, MessageRequest request);
    void deleteMessage(Long id);
}
