package com.example.ai_chatbot_integration_platform.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ai_chatbot_integration_platform.DTO.UserMapper;
import com.example.ai_chatbot_integration_platform.DTO.UserRequest;
import com.example.ai_chatbot_integration_platform.DTO.UserResponse;
import com.example.ai_chatbot_integration_platform.entity.User;
import com.example.ai_chatbot_integration_platform.exception.ResourceNotFoundException;
import com.example.ai_chatbot_integration_platform.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    UserServiceImpl(UserRepository userRepository, UserMapper userMapper){
        this.userRepository=userRepository;
        this.userMapper=userMapper;
    }

    public UserResponse createUser(UserRequest userRequest){
        User user=userMapper.toEntity(userRequest);
        User savedUser=userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    public UserResponse getUserById(Long id){
        User user=userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found with ID: "+id));

        return userMapper.toResponse(user);
    }

    public List<UserResponse> getAllUsers(){
        
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    public UserResponse updateUser(Long id,UserRequest userRequest){
        User user=userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with ID: "+id));

        user.setUserName(userRequest.getUserName());
        user.setEmail(userRequest.getEmail());
        user.setRole(userRequest.getRole());
        user.setIsActive(userRequest.getIsActive());

        User updatedUser=userRepository.save(user);

        return userMapper.toResponse(updatedUser);
    }

    public void deleteUserById(Long id){

        User user=userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with ID: "+id)); 
        userRepository.delete(user);
    }

}
