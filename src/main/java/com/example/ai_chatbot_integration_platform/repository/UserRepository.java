package com.example.ai_chatbot_integration_platform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ai_chatbot_integration_platform.entity.User;


public interface UserRepository extends JpaRepository<User,Long>{

    Optional<User> findByUserName(String username);

    Optional<User> findByEmail(String email);

    Boolean existsByUserName(String username);

}
