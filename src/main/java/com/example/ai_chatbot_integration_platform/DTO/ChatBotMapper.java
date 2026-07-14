package com.example.ai_chatbot_integration_platform.DTO;

import org.springframework.stereotype.Component;

import com.example.ai_chatbot_integration_platform.entity.ChatBot;

@Component
public class ChatBotMapper {

    public ChatBot toEntity(ChatBotRequest chatBotRequest){
        ChatBot chatBot=new ChatBot();

        chatBot.setBotName(chatBotRequest.getBotName());
        chatBot.setPlatform(chatBotRequest.getPlatform());
        chatBot.setResponse(chatBotRequest.getResponse());

        return chatBot;

    }

    public ChatBotResponse toResponse(ChatBot chatBot){
        ChatBotResponse chatBotResponse=new ChatBotResponse();

        chatBotResponse.setId(chatBot.getId());
        chatBotResponse.setBotName(chatBot.getBotName());
        chatBotResponse.setPlatform(chatBot.getPlatform());
        chatBotResponse.setResponse(chatBot.getResponse());

        if(chatBot.getOwner()!=null){
            chatBotResponse.setOwnerId(chatBot.getOwner().getId());
        }

        return chatBotResponse;
    }

}
