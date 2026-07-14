package com.example.ai_chatbot_integration_platform.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ai_chatbot_integration_platform.DTO.MessageMapper;
import com.example.ai_chatbot_integration_platform.DTO.MessageRequest;
import com.example.ai_chatbot_integration_platform.DTO.MessageResponse;
import com.example.ai_chatbot_integration_platform.entity.Conversation;
import com.example.ai_chatbot_integration_platform.entity.Message;
import com.example.ai_chatbot_integration_platform.exception.ResourceNotFoundException;
import com.example.ai_chatbot_integration_platform.repository.ConversationRepository;
import com.example.ai_chatbot_integration_platform.repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private MessageMapper messageMapper;

    MessageServiceImpl(MessageRepository messageRepository, ConversationRepository conversationRepository, MessageMapper messageMapper){
        this.messageRepository=messageRepository;
        this.conversationRepository=conversationRepository;
        this.messageMapper=messageMapper;
    }

    @Override
    public MessageResponse sendMessage(Long conversationId, MessageRequest messageRequest){
        Message message=messageMapper.toEntity(messageRequest);

        Conversation conversation=conversationRepository.findById(conversationId).orElseThrow(() -> new ResourceNotFoundException("No Conversations found with Conversation ID: "+conversationId));

        message.setConversation(conversation);

        Message savedMessage=messageRepository.save(message);

        return messageMapper.toResponse(savedMessage);
    }

    @Override
    public List<MessageResponse> getHistoryByConversationId(Long conversationId){

        return messageRepository.findByConversationIdOrderByTimestampAsc(conversationId)
                .stream()
                .map(messageMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MessageResponse getMessageById(Long id){
        Message message=messageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Message not found with ID: " + id));

        return messageMapper.toResponse(message);
    }

    @Override
    public MessageResponse updateMessage(Long id, MessageRequest messageRequest){
        Message message=messageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Message not found with ID: " + id));

        message.setSenderType(messageRequest.getSenderType());
        message.setContent(messageRequest.getContent());

        if(messageRequest.getConversationId()!=null){
            Conversation conversation=conversationRepository.findById(messageRequest.getConversationId()).orElseThrow(() -> new ResourceNotFoundException("Conversation not found"));
            message.setConversation(conversation);
        }

        Message updatedMessage=messageRepository.save(message);

        return messageMapper.toResponse(updatedMessage);
    }

    @Override
    public void deleteMessage(Long id){
        Message message=messageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Message not found with ID: " + id));

        messageRepository.delete(message);
    }
    
}
