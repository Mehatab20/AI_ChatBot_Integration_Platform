package com.example.ai_chatbot_integration_platform.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ai_chatbot_integration_platform.DTO.IntentMapper;
import com.example.ai_chatbot_integration_platform.DTO.IntentRequest;
import com.example.ai_chatbot_integration_platform.DTO.IntentResponse;
import com.example.ai_chatbot_integration_platform.entity.ChatBot;
import com.example.ai_chatbot_integration_platform.entity.Intent;
import com.example.ai_chatbot_integration_platform.exception.ResourceNotFoundException;
import com.example.ai_chatbot_integration_platform.repository.ChatBotRepository;
import com.example.ai_chatbot_integration_platform.repository.IntentRepository;

@Service
public class IntentServiceImpl implements IntentService{

    @Autowired
    IntentRepository intentRepository;

    @Autowired
    ChatBotRepository chatBotRepository;

    @Autowired
    IntentMapper intentMapper;

    IntentServiceImpl(IntentRepository intentRepository, ChatBotRepository chatBotRepository, IntentMapper intentMapper){
        this.intentRepository=intentRepository;
        this.chatBotRepository=chatBotRepository;
        this.intentMapper=intentMapper;
    }

    @Override
    public IntentResponse addIntent(IntentRequest intentRequest){
        Intent intent=intentMapper.toEntity(intentRequest);

        if(intentRequest.getBotId()!=null){
            ChatBot bot=chatBotRepository.findById(intentRequest.getBotId()).orElseThrow(() -> new ResourceNotFoundException("ChatBot not found"));
            intent.setBot(bot);
        }

        Intent savedIntent=intentRepository.save(intent);
        return intentMapper.toResponse(savedIntent);
    }

    @Override
    public IntentResponse getIntentById(Long id){
        Intent intent=intentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Intent not found with ID: " + id));
        
        return intentMapper.toResponse(intent);
    }    

    @Override
    public List<IntentResponse> getIntentsByBotId(Long botId){

        return intentRepository.findByBotId(botId)
                .stream()
                .map(intentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public IntentResponse updateIntent(Long id, IntentRequest intentRequest){
        Intent intent=intentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Intent not found with ID: " + id));
        
        intent.setName(intentRequest.getName());
        intent.setTrainingPhrases(intentRequest.getTrainingPhrases());
        intent.setResponses(intentRequest.getResponses());
        intent.setConfidence(intentRequest.getConfidence());

        
        if(intentRequest.getBotId()!=null){
            ChatBot bot=chatBotRepository.findById(intentRequest.getBotId()).orElseThrow(() -> new ResourceNotFoundException("ChatBot not found"));
            intent.setBot(bot);
        }

        Intent updatedIntent=intentRepository.save(intent);

        return intentMapper.toResponse(updatedIntent);
    }

    @Override
    public void deleteIntentById(Long id){
        Intent intent=intentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Intent not found with ID: " + id));
        intentRepository.delete(intent);
    }

}
