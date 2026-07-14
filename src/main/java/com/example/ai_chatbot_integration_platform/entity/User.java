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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor



public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String userName;

    @Column(nullable = false,unique = true)
    private String email;
    
    @Column(nullable = false)
    private String passwordHash;
    
    @Column(nullable = false)
    private String role; // ADMIN, BOT_DEVELOPER

    @Column(nullable = false)
    private Boolean isActive;


    // Mapping relationship
    //here the relationship is not created it is created in Chatbot and the chatbot is connected to the user
    //but user does'nt know that it has many chatbots for one user so we are telling it is onetomany mapping 
    //not creating relationship but if user wants to access chatbots it navigates using List<Chatbots> chatbots
    //but the relationship is created in
    /*
    @ManyToOne
    @JoinColumn(name="owner_id")
    cascade = CascadeType.ALL -> this means if the user is updated,created,deleted,saved,merged or refresh the chatbot also should do it 
    if not then suppose user deleted then chatbot points to nothing bad databse
    fetch = FetchType.LAZY -> without this if you have 10,000 bots then you will also have 10,000 chatbots when you want to get user 1 then it will
    process all 10,000 bots instead of what needed to avoid that you use LAZY to first load only user ignore chatbots for now
    and then get the chatbot of the particulat user this saves memory
    */
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    List<ChatBot> chatbots;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Conversation> conversations;

}
