package com.prlab.activityWeb.controller.service;

import com.prlab.activityWeb.model.Activity;
import com.prlab.activityWeb.model.DTO.RequestActivity;
import com.prlab.activityWeb.model.DTO.SearchString;
import com.prlab.activityWeb.model.User;
import com.prlab.activityWeb.model.repository.ActivityRepository;
import com.prlab.activityWeb.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/service/activity")
public class ActivityController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ActivityRepository activityRepository;

    @PostMapping("/create")
    public String createActivity(Authentication authentication, RequestActivity requestActivity, Model model){
        User user = userRepository.findByUsername(authentication.getName());
        Activity activity = new Activity();
        try {
            Date startDate  = new SimpleDateFormat("yyyy-MM-dd").parse(requestActivity.getStartTime());
            Date endDate  = new SimpleDateFormat("yyyy-MM-dd").parse(requestActivity.getEndTime());
            if(startDate.compareTo(endDate) > 0){
                model.addAttribute("error","活動的結束時間應晚於開始時間");
                return "error";
            }
            activity.setStartTime(startDate);
            activity.setEndTime(endDate);
        } catch (ParseException e){

        }
        activity.setActivityName(requestActivity.getActivityName());
        activity.setActivityType(requestActivity.getActivityType());
        activity.setLocation(requestActivity.getLocation());
        activity.setRule(requestActivity.getRule());
        activity.setCreatedDate(new Date());
        activity.setOwner(user);

        activityRepository.save(activity);
        return "redirect:/activity";
    }

    @GetMapping("/delete/{Id}")
    public String deleteActivity(@PathVariable(value="Id") String Id, Authentication authentication){
        Optional<Activity> activity = activityRepository.findById(Integer.parseInt(Id));
        User user = userRepository.findByUsername(authentication.getName());
        if (activity.isPresent()) {
            if (activity.get().getOwner().getId() == user.getId() || user.getRole()=="admin") {
                activityRepository.delete(activity.get());
                return "redirect:/activity";
            }
        }
        return "error";
    }

    @PostMapping("/search")
    public String searchActivity(SearchString searchString, Authentication authentication, Model model){
        User user = userRepository.findByUsername(authentication.getName());
        List<Activity> activities;
        if(user.getRole() == "admin"){
            activities = activityRepository.findByActivityNameContaining(searchString.getKeyword());
        }
        else{
            activities = activityRepository.findByOwner_idAndActivityNameContaining(user.getId(),searchString.getKeyword());
        }

        Date now = new Date();
        List<Activity> continued = new ArrayList<>();
        List<Activity> expired = new ArrayList<>();;
        for(Activity eachActivity:activities){
            if(eachActivity.getEndTime().compareTo(now) > 0){
                continued.add(eachActivity);
            }else{
                expired.add(eachActivity);
            }
        }
        if(continued.size()>=1)
            model.addAttribute("continued",continued);
        if (expired.size()>=1)
            model.addAttribute("expired",expired);

        return "activity";
    }
}
