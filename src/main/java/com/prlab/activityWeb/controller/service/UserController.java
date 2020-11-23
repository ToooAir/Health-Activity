package com.prlab.activityWeb.controller.service;

import com.prlab.activityWeb.model.User;
import com.prlab.activityWeb.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/service/user")
public class UserController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public String createUser(Authentication authentication, User user, Model model){
        user.setRole("user");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/user";
    }

    @GetMapping("/delete/{Id}")
    public String deleteUser(@PathVariable(value="Id") String Id, Authentication authentication){
        userRepository.deleteById(Integer.parseInt(Id));
        return "redirect:/user";
    }
}