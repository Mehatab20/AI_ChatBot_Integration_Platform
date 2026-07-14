package com.example.ai_chatbot_integration_platform.service;

import java.util.List;

import com.example.ai_chatbot_integration_platform.DTO.ChannelRequest;
import com.example.ai_chatbot_integration_platform.DTO.ChannelResponse;

public interface ChannelService {

    ChannelResponse deployChannel(ChannelRequest channelRequest);

    ChannelResponse getChannelById(Long id);

    List<ChannelResponse> getAllChannels();

    List<ChannelResponse> getChannelsByBotId(Long botId);

    ChannelResponse updateChannel(Long id, ChannelRequest channelRequest);

    void removeChannel(Long id);
    
}
