package com.india.GatedSocietyApp.controllers;

import com.india.GatedSocietyApp.entity.User;
import com.india.GatedSocietyApp.requests.LoginRequest;
import com.india.GatedSocietyApp.requests.SignUpRequest;
import com.india.GatedSocietyApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth/")
public class LoginController {

    @Autowired
    public UserService userService;

    @PostMapping(value = "/login")
    public String login(@RequestBody @Valid LoginRequest loginRequest) {
        return userService.login(loginRequest.getEmail(), loginRequest.getPassword())
                .orElseThrow(() ->
                        new HttpServerErrorException(HttpStatus.FORBIDDEN, "Login Failed"));

    }

    @PostMapping(value = "/signup")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User signup(@RequestBody @Valid SignUpRequest signUpRequest) {
        return userService.signup(signUpRequest)
                .orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "User Already Exists"));
    }
}
