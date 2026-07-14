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

import com.example.ai_chatbot_integration_platform.DTO.ConversationRequest;
import com.example.ai_chatbot_integration_platform.DTO.ConversationResponse;
import com.example.ai_chatbot_integration_platform.service.ConversationService;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {

    @Autowired
    private ConversationService conversationService;

    ConversationController(ConversationService conversationService){
        this.conversationService=conversationService;
    }

    @PostMapping
    public ResponseEntity<ConversationResponse> startConversation(@RequestBody ConversationRequest conversationRequest){
        ConversationResponse conversationResponse=conversationService.startConversation(conversationRequest);

        return new ResponseEntity<>(conversationResponse,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConversationResponse> getConversationById(@PathVariable Long id){
        ConversationResponse conversationResponse=conversationService.getConversationById(id);

        return ResponseEntity.ok(conversationResponse);
    }

    @GetMapping("/bots/{botId}")
    public ResponseEntity<List<ConversationResponse>> getAllConversationsByBotId(@PathVariable Long botId){
        List<ConversationResponse> conversationResponses=conversationService.getAllConversationsByBotId(botId);

        return ResponseEntity.ok(conversationResponses);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<ConversationResponse>> getAllConversationsByUserId(@PathVariable Long userId){
        List<ConversationResponse> conversationResponses=conversationService.getAllConversationsByUserId(userId);

        return ResponseEntity.ok(conversationResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConversationResponse> updateConversation(@PathVariable Long id, @RequestBody ConversationRequest conversationRequest){
        ConversationResponse conversationResponse=conversationService.updateConversation(id, conversationRequest);

        return ResponseEntity.ok(conversationResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConversationById(@PathVariable Long id){
        conversationService.deleteConversationById(id);

        return ResponseEntity.noContent().build();
    }

}
