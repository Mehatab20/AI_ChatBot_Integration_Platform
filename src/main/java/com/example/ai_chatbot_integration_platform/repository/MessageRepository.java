package com.example.ai_chatbot_integration_platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ai_chatbot_integration_platform.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{

    List<Message> findByConversationIdOrderByTimestampAsc(Long conversationId);
}
