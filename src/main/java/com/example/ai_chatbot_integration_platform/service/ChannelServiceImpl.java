package com.example.ai_chatbot_integration_platform.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ai_chatbot_integration_platform.DTO.ChannelMapper;
import com.example.ai_chatbot_integration_platform.DTO.ChannelRequest;
import com.example.ai_chatbot_integration_platform.DTO.ChannelResponse;
import com.example.ai_chatbot_integration_platform.entity.Channel;
import com.example.ai_chatbot_integration_platform.entity.ChatBot;
import com.example.ai_chatbot_integration_platform.exception.ResourceNotFoundException;
import com.example.ai_chatbot_integration_platform.repository.ChannelRepository;
import com.example.ai_chatbot_integration_platform.repository.ChatBotRepository;

@Service
public class ChannelServiceImpl implements ChannelService{

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private ChatBotRepository chatBotRepository;

    @Autowired 
    private ChannelMapper channelMapper;

    ChannelServiceImpl(ChannelRepository channelRepository, ChatBotRepository chatBotRepository, ChannelMapper channelMapper){
        this.channelRepository=channelRepository;
        this.chatBotRepository=chatBotRepository;
        this.channelMapper=channelMapper;
    }

    @Override
    public ChannelResponse deployChannel(ChannelRequest channelRequest){
        Channel channel=channelMapper.toEntity(channelRequest);

        if(channelRequest.getBotId()!=null){
            ChatBot chatBot=chatBotRepository.findById(channelRequest.getBotId()).orElseThrow(() -> new ResourceNotFoundException("ChatBot not found"));

            channel.setBot(chatBot);
        }

        Channel savedChannel=channelRepository.save(channel);

        return channelMapper.toResponse(savedChannel);
    }

    @Override
    public ChannelResponse getChannelById(Long id){
        Channel channel=channelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Channel not found with ID: " + id));

        return channelMapper.toResponse(channel);
    }

    @Override
    public List<ChannelResponse> getAllChannels(){
        
        List<Channel> channels=channelRepository.findAll();

        if(channels.isEmpty()){
            throw new ResourceNotFoundException("No Channels found ");
        }
        
        return channels
                .stream()
                .map(channelMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChannelResponse> getChannelsByBotId(Long botId){

        return channelRepository.findByBotId(botId)
            .stream()
            .map(channelMapper::toResponse)
            .collect(Collectors.toList());
    }

    @Override
    public ChannelResponse updateChannel(Long id, ChannelRequest channelRequest){
        Channel channel=channelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Channel not found with ID: " + id));

        if(channelRequest.getChannelType()!=null){
            channel.setChannelType(channelRequest.getChannelType());
        }

        if(channelRequest.getBotId()!=null){
            ChatBot bot=chatBotRepository.findById(channelRequest.getBotId()).orElseThrow(() -> new ResourceNotFoundException("ChatBot not found"));

            channel.setBot(bot);
        }

        Channel updatedChannel=channelRepository.save(channel);

        return channelMapper.toResponse(updatedChannel);
    }

    @Override
    public void removeChannel(Long id){
        Channel channel=channelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Channel not found with ID: " + id));
        
        channelRepository.delete(channel);
    }

}
