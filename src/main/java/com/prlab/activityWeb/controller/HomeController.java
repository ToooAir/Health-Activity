package com.prlab.activityWeb.controller;

import com.prlab.activityWeb.model.Activity;
import com.prlab.activityWeb.model.User;
import com.prlab.activityWeb.model.repository.ActivityRepository;
import com.prlab.activityWeb.model.repository.HealthDataRepository;
import com.prlab.activityWeb.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping
public class HomeController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private HealthDataRepository healthDataRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/activity")
    public String getActivityPage(Activity activity,Authentication authentication, Model model){
        model.addAttribute("activity",activityRepository.findAll());
        model.addAttribute("name",userRepository.findByUsername(authentication.getName()).getDisplayName());
        return "activity";
    }

    @GetMapping("/user")
    public String getUserPage(User user,Authentication authentication, Model model){
        model.addAttribute("user",userRepository.findAll());

        model.addAttribute("name",userRepository.findByUsername(authentication.getName()).getDisplayName());
        return "user";
    }

    @GetMapping("/createUser")
    public String getCreateUserPage(User user,Authentication authentication ,Model model){
        model.addAttribute("name",userRepository.findByUsername(authentication.getName()).getDisplayName());
        return "createUser";
    }

    @GetMapping("/createActivity")
    public String getCreateActivityPage(Activity activity,Authentication authentication ,Model model){
        model.addAttribute("name",userRepository.findByUsername(authentication.getName()).getDisplayName());
        return "createActivity";
    }

    @GetMapping("/activityDetail/{Id}")
    public String getActivityDetailPage(@PathVariable(value="Id") String Id, Authentication authentication, Model model){
        Optional<Activity> activity = activityRepository.findById(Integer.parseInt(Id));
        activity.ifPresent(foundActivity -> model.addAttribute("activity",foundActivity));
        model.addAttribute("healthData",healthDataRepository.findAllByActivity_id(Integer.parseInt(Id)));

        model.addAttribute("name",userRepository.findByUsername(authentication.getName()).getDisplayName());
        return "activityDetail";
    }

}

