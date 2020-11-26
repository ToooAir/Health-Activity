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

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    public String adminGetActivityPage(Activity activity,Authentication authentication, Model model){
        User user = userRepository.findByUsername(authentication.getName());
        List<Activity> activities;
        if(user.getRole() == "admin"){
            activities = activityRepository.findAll();
        }
        else{
            activities = activityRepository.findAllByOwner_id(user.getId());
        }
        Date now = new Date();
        List<Activity> continued = new ArrayList<>();
        List<Activity> expired = new ArrayList<>();
        int i = 0;
        while (i < activities.size()){
            if(activities.get(i).getEndTime().compareTo(now) > 0){
                continued.add(activities.get(i));
            }else{
                expired.add(activities.get(i));
            }
            i++;
        }
        model.addAttribute("continued",continued);
        model.addAttribute("expired",expired);
        return "activity";
    }

    @RolesAllowed("admin")
    @GetMapping("/user")
    public String getUserPage(User user,Authentication authentication, Model model){
        model.addAttribute("user",userRepository.findAll());
        return "user";
    }

    @RolesAllowed("admin")
    @GetMapping("/createUser")
    public String getCreateUserPage(User user,Authentication authentication ,Model model){
        return "createUser";
    }

    @GetMapping("/createActivity")
    public String getCreateActivityPage(Activity activity,Authentication authentication ,Model model){
        return "createActivity";
    }

    @GetMapping("/activityDetail/{Id}")
    public String getActivityDetailPage(@PathVariable(value="Id") String Id, Authentication authentication, Model model) {
        Optional<Activity> activity = activityRepository.findById(Integer.parseInt(Id));
        User user = userRepository.findByUsername(authentication.getName());
        if (activity.isPresent()) {
            if (activity.get().getOwner().getId() == user.getId() || user.getRole()=="admin") {
                model.addAttribute("activity", activity.get());
                model.addAttribute("healthData", healthDataRepository.findAllByActivity_id(Integer.parseInt(Id)));
                return "activityDetail";
            }
        }
        return "error";
    }
}

