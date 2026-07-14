package com.example.ai_chatbot_integration_platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ai_chatbot_integration_platform.DTO.ChatBotRequest;
import com.example.ai_chatbot_integration_platform.DTO.ChatBotResponse;
import com.example.ai_chatbot_integration_platform.service.ChatBotService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/chatbots")
public class ChatBotController {

    @Autowired
    private ChatBotService chatBotService;

    ChatBotController(ChatBotService chatBotService){
        this.chatBotService=chatBotService;
    }

    @PostMapping
    public ResponseEntity<ChatBotResponse> addChatBot(@RequestBody ChatBotRequest chatBotRequest) {
        ChatBotResponse savedBotResponse=chatBotService.addChatBot(chatBotRequest);
        return new ResponseEntity<>(savedBotResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ChatBotResponse>> getAllChatBots() {
        List<ChatBotResponse> savedBotResponse=chatBotService.getAllChatBots();
        return ResponseEntity.ok(savedBotResponse);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ChatBotResponse> getChatBotById(@PathVariable Long id) {
        ChatBotResponse savBotResponse=chatBotService.getChatBotById(id);

        return ResponseEntity.ok(savBotResponse);
    }
    
    @GetMapping("/integration/{platform}")
    public ResponseEntity<List<ChatBotResponse>> getIntegrationStatus(@PathVariable String platform) {
        List<ChatBotResponse> savedBotResponse=chatBotService.getIntegrationStatus(platform);
        return ResponseEntity.ok(savedBotResponse);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ChatBotResponse> updateChatBot(@PathVariable Long id, @RequestBody ChatBotRequest chatBotRequest) {
        ChatBotResponse chatBotResponse=chatBotService.updateChatBot(id, chatBotRequest);
        return ResponseEntity.ok(chatBotResponse);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChatBotById(@PathVariable Long id){
        chatBotService.deleteChatBot(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/users/{ownerId}")
    public ResponseEntity<List<ChatBotResponse>> getUserChatBots(@PathVariable Long ownerId) {
        List<ChatBotResponse> savedBotResponse=chatBotService.getChatBotsByOwnerId(ownerId);
        return ResponseEntity.ok(savedBotResponse);
    }
    
}
