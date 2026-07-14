package com.example.ai_chatbot_integration_platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ai_chatbot_integration_platform.entity.Channel;

public interface ChannelRepository extends JpaRepository<Channel, Long> {

    List<Channel> findByBotId(Long botId);
}
