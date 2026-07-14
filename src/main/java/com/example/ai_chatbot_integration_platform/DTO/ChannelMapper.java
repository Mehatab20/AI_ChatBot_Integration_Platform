package com.example.ai_chatbot_integration_platform.DTO;

import org.springframework.stereotype.Component;

import com.example.ai_chatbot_integration_platform.entity.Channel;

@Component
public class ChannelMapper {

    public Channel toEntity(ChannelRequest channelRequest){
        Channel channel=new Channel();

        channel.setChannelType(channelRequest.getChannelType());
        channel.setDeploymentStatus("DEPLOYED");

        return channel;

    }

    public ChannelResponse toResponse(Channel channel){
        ChannelResponse channelResponse=new ChannelResponse();

        channelResponse.setId(channel.getId());
        channelResponse.setChannelType(channel.getChannelType());
        channelResponse.setDeploymentStatus(channel.getDeploymentStatus());

        if(channel.getBot()!=null){
            channelResponse.setBotId(channel.getBot().getId());
        }

        return channelResponse;
    }

}
