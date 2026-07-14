package com.example.ai_chatbot_integration_platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ai_chatbot_integration_platform.entity.Intent;

public interface IntentRepository extends JpaRepository<Intent,Long>{

    List<Intent> findByBotId(Long botId);
}
