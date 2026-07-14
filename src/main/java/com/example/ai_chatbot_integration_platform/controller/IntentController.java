package com.example.ai_chatbot_integration_platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ai_chatbot_integration_platform.DTO.IntentRequest;
import com.example.ai_chatbot_integration_platform.DTO.IntentResponse;
import com.example.ai_chatbot_integration_platform.service.IntentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/intents")
public class IntentController {

    @Autowired
    private IntentService intentService;

    IntentController(IntentService intentService){
        this.intentService=intentService;
    }

    @PostMapping
    public ResponseEntity<IntentResponse> addIntent(@RequestBody IntentRequest intentRequest) {
        
        IntentResponse intentResponse=intentService.addIntent(intentRequest);
        return new ResponseEntity<>(intentResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IntentResponse> getIntentById(@PathVariable Long id) {
        IntentResponse intentResponse = intentService.getIntentById(id);

        return ResponseEntity.ok(intentResponse);
    }
    
    @GetMapping("/bots/{botId}")
    public ResponseEntity<List<IntentResponse>> getIntentByBotId(@PathVariable Long botId) {
        List<IntentResponse> intentResponse = intentService.getIntentsByBotId(botId);

        return ResponseEntity.ok(intentResponse);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<IntentResponse> updateIntent(@PathVariable Long id, @RequestBody IntentRequest intentRequest) {
        IntentResponse intentResponse=intentService.updateIntent(id, intentRequest);
        
        return ResponseEntity.ok(intentResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIntentById(@PathVariable Long id){
        intentService.deleteIntentById(id);

        return ResponseEntity.noContent().build();
    }
    
}
