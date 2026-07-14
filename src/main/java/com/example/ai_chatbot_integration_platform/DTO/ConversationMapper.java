package com.example.ai_chatbot_integration_platform.DTO;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.example.ai_chatbot_integration_platform.entity.Conversation;

@Component
public class ConversationMapper {

    public Conversation toEntity(ConversationRequest conversationRequest){
        Conversation conversation=new Conversation();

        conversation.setStartTime(LocalDateTime.now());
        conversation.setStatus("ACTIVE");

        return conversation;

    }

    public ConversationResponse toResponse(Conversation conversation){
        ConversationResponse conversationResponse=new ConversationResponse();

        conversationResponse.setId(conversation.getId());
        conversationResponse.setStartTime(conversation.getStartTime());
        conversationResponse.setStatus(conversation.getStatus());
        

        if(conversation.getBot()!=null){
            conversationResponse.setBotId(conversation.getBot().getId());
        }
        
        if(conversation.getUser()!=null){
            conversationResponse.setUserId(conversation.getUser().getId());
        }

        return conversationResponse;
    }

}
