package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    // 모든 사용자 조회
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    // ID로 사용자 조회
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    // 사용자 생성
    public User createUser(User user) {
        return userRepository.save(user);
    }
    
    // 사용자 수정
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        user.setName(userDetails.getName());
        user.setAge(userDetails.getAge());
        user.setEmail(userDetails.getEmail());
        
        return userRepository.save(user);
    }
    
    // 사용자 삭제
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}