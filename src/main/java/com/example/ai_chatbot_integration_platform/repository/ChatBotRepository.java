package com.example.ai_chatbot_integration_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ai_chatbot_integration_platform.entity.ChatBot;
import java.util.List;


public interface ChatBotRepository extends JpaRepository<ChatBot,Long>{

    List<ChatBot> findByPlatform(String platform);

    List<ChatBot> findByOwnerId(Long ownerId);
}
