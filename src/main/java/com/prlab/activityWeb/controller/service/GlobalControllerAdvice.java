package com.prlab.activityWeb.controller.service;

import com.prlab.activityWeb.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute("name")
    public String displayUsername(Authentication authentication){
        String name = "";
        if (authentication != null){
            name = userRepository.findByUsername(authentication.getName()).getDisplayName();
        }
        return name;
    }

    @ModelAttribute("role")
    public String role(Authentication authentication){
        String role = "";
        if (authentication != null){
            role = userRepository.findByUsername(authentication.getName()).getRole();
        }
        return role;
    }


}
