package com.example.ai_chatbot_integration_platform.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ai_chatbot_integration_platform.DTO.ConversationMapper;
import com.example.ai_chatbot_integration_platform.DTO.ConversationRequest;
import com.example.ai_chatbot_integration_platform.DTO.ConversationResponse;
import com.example.ai_chatbot_integration_platform.entity.ChatBot;
import com.example.ai_chatbot_integration_platform.entity.Conversation;
import com.example.ai_chatbot_integration_platform.entity.User;
import com.example.ai_chatbot_integration_platform.exception.ResourceNotFoundException;
import com.example.ai_chatbot_integration_platform.repository.ChatBotRepository;
import com.example.ai_chatbot_integration_platform.repository.ConversationRepository;
import com.example.ai_chatbot_integration_platform.repository.UserRepository;

@Service
public class ConversationServiceImpl implements ConversationService{

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatBotRepository chatBotRepository;

    @Autowired
    private ConversationMapper conversationMapper;

    ConversationServiceImpl(ConversationRepository conversationRepository, UserRepository userRepository, ChatBotRepository chatBotRepository, ConversationMapper conversationMapper){
        this.conversationRepository=conversationRepository;
        this.userRepository=userRepository;
        this.chatBotRepository=chatBotRepository;
        this.conversationMapper=conversationMapper;
    }

    @Override
    public ConversationResponse startConversation(ConversationRequest conversationRequest){

        Conversation conversation=conversationMapper.toEntity(conversationRequest);

        if(conversationRequest.getBotId()!=null){
            ChatBot chatBot=chatBotRepository.findById(conversationRequest.getBotId()).orElseThrow(() -> new ResourceNotFoundException("ChatBot not found with Bot ID: "));

            conversation.setBot(chatBot);
        }

        if(conversationRequest.getUserId()!=null){
            User user=userRepository.findById(conversationRequest.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));

            conversation.setUser(user);
        }

        Conversation savedConversation=conversationRepository.save(conversation);
        
        return conversationMapper.toResponse(savedConversation);

    }

    @Override
    public ConversationResponse getConversationById(Long id){
        Conversation conversation=conversationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Conversation not found with ID: " + id));

        return conversationMapper.toResponse(conversation);
    }

    @Override
    public List<ConversationResponse> getAllConversationsByBotId(Long botId){

        return conversationRepository.findByBotId(botId)
            .stream()
            .map(conversationMapper::toResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<ConversationResponse> getAllConversationsByUserId(Long userId){

        return conversationRepository.findByUserId(userId)
            .stream()
            .map(conversationMapper::toResponse)
            .collect(Collectors.toList());
    }

    @Override
    public ConversationResponse updateConversation(Long id, ConversationRequest conversationRequest){
        Conversation conversation=conversationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Conversation not found with ID: " + id));

        if(conversationRequest.getBotId()!=null){
            ChatBot bot=chatBotRepository.findById(conversationRequest.getBotId()).orElseThrow(() -> new ResourceNotFoundException("ChatBot not found"));
            conversation.setBot(bot);
        }
       
        if(conversationRequest.getUserId()!=null){
            User user=userRepository.findById(conversationRequest.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
            conversation.setUser(user);
        }

        Conversation updatedConversation=conversationRepository.save(conversation);

        return conversationMapper.toResponse(updatedConversation);
        
    }

    @Override
    public void deleteConversationById(Long id){
        Conversation conversation=conversationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Conversation not found with ID: " + id));

        conversationRepository.delete(conversation);
    }

}
