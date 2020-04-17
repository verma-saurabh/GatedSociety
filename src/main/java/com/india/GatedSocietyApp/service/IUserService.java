package com.india.GatedSocietyApp.service;

import com.india.GatedSocietyApp.entity.User;
import com.india.GatedSocietyApp.requests.SignUpRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends UserDetailsService {

    Optional<User> findByEmail(String email);

    User save(SignUpRequest signUpRequest);
}
