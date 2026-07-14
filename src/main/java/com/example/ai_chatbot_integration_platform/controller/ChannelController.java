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

import com.example.ai_chatbot_integration_platform.DTO.ChannelRequest;
import com.example.ai_chatbot_integration_platform.DTO.ChannelResponse;
import com.example.ai_chatbot_integration_platform.service.ChannelService;

@RestController
@RequestMapping("/api/channels")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    ChannelController(ChannelService channelService){
        this.channelService=channelService;
    }

    
    @PostMapping
    public ResponseEntity<ChannelResponse> deployChannel(@RequestBody ChannelRequest channelRequest){
       ChannelResponse channelResponse=channelService.deployChannel(channelRequest);

       return new ResponseEntity<>(channelResponse, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ChannelResponse> getChannelById(@PathVariable Long id){
        ChannelResponse channelResponse=channelService.getChannelById(id);

        return ResponseEntity.ok(channelResponse);

    }

    @GetMapping
    public ResponseEntity<List<ChannelResponse>> getAllChannels(){
        List<ChannelResponse> channelResponses=channelService.getAllChannels();

        return ResponseEntity.ok(channelResponses);
    }

    @GetMapping("/bots/{botId}")
    public ResponseEntity<List<ChannelResponse>> getChannelsByBotId(@PathVariable Long botId){
        List<ChannelResponse> channelResponses=channelService.getChannelsByBotId(botId);

        return ResponseEntity.ok(channelResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChannelResponse> updateChannel(@PathVariable Long id, @RequestBody ChannelRequest channelRequest){
        ChannelResponse updatedchannelResponse=channelService.updateChannel(id, channelRequest);
        return ResponseEntity.ok(updatedchannelResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeChannel(@PathVariable Long id){
        channelService.removeChannel(id);
        
        return ResponseEntity.noContent().build();
    }

}
