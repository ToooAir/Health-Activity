package com.prlab.activityWeb.controller.service;

import com.prlab.activityWeb.model.DTO.SearchString;
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

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/service/user")
public class UserController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @RolesAllowed("admin")
    @PostMapping("/create")
    public String createUser(Authentication authentication, User user, Model model){
        if(userRepository.findByUsername(user.getUsername())==null){
            user.setRole("user");
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return "redirect:/user";
        }
        model.addAttribute("error","請勿創建相同帳號的管理員");
        return "error";
    }

    @RolesAllowed("admin")
    @GetMapping("/delete/{Id}")
    public String deleteUser(@PathVariable(value="Id") String Id, Authentication authentication){
        userRepository.deleteById(Integer.parseInt(Id));
        return "redirect:/user";
    }

    @RolesAllowed("admin")
    @PostMapping("/search")
    public String searchUser(SearchString searchString, Authentication authentication, Model model){
        List<User> searchUser = userRepository.findByUsernameContainingOrDisplayNameContaining(searchString.getKeyword(),searchString.getKeyword());

        if (searchUser.size() >= 1) model.addAttribute("user",searchUser);

        return "user";
    }
}