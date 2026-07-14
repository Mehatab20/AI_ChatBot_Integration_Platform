package com.example.ai_chatbot_integration_platform.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "chatbots")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ChatBot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String botName;

    @Column(nullable = false)
    private String platform;
    
    @Column(nullable = false)
    private String response;
    
    //Relational Mapping
    
    @ManyToOne(fetch = FetchType.LAZY)
    //relationship is created here only 
    @JoinColumn(name = "owner_id")//column name , foreign key
    //to access the id and all the properties of the parent class
    // we are mentioning a object to the parent class because java knows only obj not foreign keys
    private User owner;

    //mapping relationship
    @OneToMany(mappedBy = "bot", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Intent> nlpIntents;

    @OneToMany(mappedBy = "bot",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Channel> channels;

    @OneToMany(mappedBy = "bot", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Conversation> conversations;
    
}
