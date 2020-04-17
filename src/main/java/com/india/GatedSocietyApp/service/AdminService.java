package com.india.GatedSocietyApp.service;

import com.india.GatedSocietyApp.entity.User;
import com.india.GatedSocietyApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    public UserRepository userRepository;
    public void addUser() {

    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
