package com.example.ai_chatbot_integration_platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ai_chatbot_integration_platform.entity.Conversation;

public interface ConversationRepository extends JpaRepository<Conversation,Long>{

    List<Conversation> findByUserId(Long userId);

    List<Conversation> findByBotId(Long botId);
}
