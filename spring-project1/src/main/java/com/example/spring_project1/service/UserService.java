package com.example.spring_project1.service;

import com.example.spring_project1.model.User;
import com.example.spring_project1.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    public UserRepo userRepo;

    public User getUserByName(String name) {
        return userRepo.findByName(name);
    }
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    public User CreateUser(User user) {
        return userRepo.save(user);
    }
    public User updateUser(Long id, User user) {
        if (userRepo.existsById(id)) {
            user.setId(id);
            return userRepo.save(user);
        }
        return null; // or throw an exception
    }
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
