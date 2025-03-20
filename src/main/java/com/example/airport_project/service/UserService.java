package com.example.airport_project.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.airport_project.model.User;
import com.example.airport_project.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationDto registrationDto);
}

