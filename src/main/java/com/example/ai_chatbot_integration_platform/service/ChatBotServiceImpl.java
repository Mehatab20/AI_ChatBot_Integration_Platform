package com.example.ai_chatbot_integration_platform.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ai_chatbot_integration_platform.DTO.ChatBotMapper;
import com.example.ai_chatbot_integration_platform.DTO.ChatBotRequest;
import com.example.ai_chatbot_integration_platform.DTO.ChatBotResponse;
import com.example.ai_chatbot_integration_platform.entity.ChatBot;
import com.example.ai_chatbot_integration_platform.entity.User;
import com.example.ai_chatbot_integration_platform.exception.ResourceNotFoundException;
import com.example.ai_chatbot_integration_platform.repository.ChatBotRepository;
import com.example.ai_chatbot_integration_platform.repository.UserRepository;

@Service
public class ChatBotServiceImpl implements ChatBotService{

    @Autowired
    private ChatBotRepository chatBotRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired 
    private ChatBotMapper chatBotMapper;

    ChatBotServiceImpl(ChatBotRepository chatBotRepository, ChatBotMapper chatBotMapper, UserRepository userRepository){
        this.chatBotRepository=chatBotRepository;
        this.chatBotMapper=chatBotMapper;
        this.userRepository=userRepository;
    }

    @Override
    public ChatBotResponse addChatBot(ChatBotRequest chatBotRequest){
        ChatBot bot=chatBotMapper.toEntity(chatBotRequest);

        if(chatBotRequest.getOwnerId()!=null){
            User owner=userRepository.findById(chatBotRequest.getOwnerId()).orElseThrow(() -> new ResourceNotFoundException("Owner not found"));

            bot.setOwner(owner);
        }
        
        ChatBot savedBot=chatBotRepository.save(bot);

        return chatBotMapper.toResponse(savedBot);
    }

    @Override
    public ChatBotResponse getChatBotById(Long id){
        ChatBot bot=chatBotRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ChatBot not found with ID: " + id));

        return chatBotMapper.toResponse(bot);
    }

    @Override
    public List<ChatBotResponse> getChatBotsByOwnerId(Long ownerId){

        return chatBotRepository.findByOwnerId(ownerId)
                .stream()
                .map(chatBotMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChatBotResponse> getAllChatBots(){

        return chatBotRepository.findAll()
                .stream()
                .map(chatBotMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChatBotResponse> getIntegrationStatus(String platform){

        List<ChatBot> bots=chatBotRepository.findByPlatform(platform);

        if(bots.isEmpty()){
            throw new ResourceNotFoundException("No ChatBots found for Platform: "+platform);
        }
        return chatBotRepository.findByPlatform(platform)
                .stream()
                .map(chatBotMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ChatBotResponse updateChatBot(Long id,ChatBotRequest chatBotRequest){
        ChatBot bot=chatBotRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("ChatBot not found with ID: " + id));

        bot.setBotName(chatBotRequest.getBotName());
        bot.setPlatform(chatBotRequest.getPlatform());
        bot.setResponse(chatBotRequest.getResponse());

        if(chatBotRequest.getOwnerId()!=null){
            User owner=userRepository.findById(chatBotRequest.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("Owner User not found"));

            bot.setOwner(owner);
        }

        ChatBot updatedBot=chatBotRepository.save(bot);

        return chatBotMapper.toResponse(updatedBot);
    }

    @Override
    public void deleteChatBot(Long id){

        ChatBot chatBot=chatBotRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ChatBot not found with ID: " + id));
        chatBotRepository.delete(chatBot);
    }

}
