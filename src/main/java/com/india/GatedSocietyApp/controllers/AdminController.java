package com.india.GatedSocietyApp.controllers;

import com.india.GatedSocietyApp.beans.Users.UserDto;
import com.india.GatedSocietyApp.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/Admin/")
public class AdminController {
    private final ModelMapper modelMapper;

    @Autowired
    private AdminService adminService;

    @GetMapping(value = "/users")
    //@PreAuthorize("@securityConfig.hasRole('READ_ADMIN')")
    @PreAuthorize("@jwtTokenFilter.hasRole('ROLE_ADMIN')")
    public List<UserDto> usersAttribute() {
        return adminService.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, UserDto.class))
                .collect(Collectors.toList());
    }
}
