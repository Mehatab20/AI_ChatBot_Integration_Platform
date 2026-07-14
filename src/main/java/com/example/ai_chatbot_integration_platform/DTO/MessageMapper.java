package com.example.ai_chatbot_integration_platform.DTO;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.example.ai_chatbot_integration_platform.entity.Message;

@Component
public class MessageMapper {

    public Message toEntity(MessageRequest messageRequest){
        Message message=new Message();

        message.setContent(messageRequest.getContent());
        message.setSenderType(messageRequest.getSenderType());
        message.setTimestamp(LocalDateTime.now());

        return message;

    }

    public MessageResponse toResponse(Message message){
        MessageResponse messageResponse=new MessageResponse();

        messageResponse.setId(message.getId());
        messageResponse.setContent(message.getContent());
        messageResponse.setSenderType(message.getSenderType());
        messageResponse.setTimestamp(message.getTimestamp());

        if(message.getConversation()!=null){
            messageResponse.setConversationId(message.getConversation().getId());
        }

        return messageResponse;
    }

}
