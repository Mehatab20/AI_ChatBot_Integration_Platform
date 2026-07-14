package com.example.ai_chatbot_integration_platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ai_chatbot_integration_platform.DTO.MessageRequest;
import com.example.ai_chatbot_integration_platform.DTO.MessageResponse;
import com.example.ai_chatbot_integration_platform.service.MessageService;

@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private MessageService messageService;

    MessageController(MessageService messageService){
        this.messageService=messageService;
    }
    
    @PostMapping("/conversations/{conversationId}/messages")
    public ResponseEntity<MessageResponse> sendMessage(@PathVariable Long conversationId, @RequestBody MessageRequest messageRequest){
        MessageResponse messageResponse=messageService.sendMessage(conversationId, messageRequest);

        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }

    @GetMapping("/conversations/{conversationId}/messages")
    public ResponseEntity<List<MessageResponse>> getHistoryByConversationId(@PathVariable Long conversationId){
        List<MessageResponse> history=messageService.getHistoryByConversationId(conversationId);

        return ResponseEntity.ok(history);
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<MessageResponse> getMessageById(@PathVariable Long id){
        MessageResponse messageResponse=messageService.getMessageById(id);

        return ResponseEntity.ok(messageResponse);
    }

    @PutMapping("/messages/{id}")
    public ResponseEntity<MessageResponse> updateMessage(@PathVariable Long id, @RequestBody MessageRequest messageRequest){
        MessageResponse messageResponse=messageService.updateMessage(id, messageRequest);

        return ResponseEntity.ok(messageResponse);
    }

    @DeleteMapping("/messages/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id){
        messageService.deleteMessage(id);

        return ResponseEntity.noContent().build();
    }

}
