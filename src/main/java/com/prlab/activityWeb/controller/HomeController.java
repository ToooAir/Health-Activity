package com.prlab.activityWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {

    @GetMapping("/activity")
    public String getActivityPage(){
        return "activity";
    }

    @GetMapping("/user")
    public String getUserPage(){
        return "user";
    }

    @GetMapping("/createUser")
    public String getCreateUserPage(){
        return "createUser";
    }

    @GetMapping("/createActivity")
    public String getCreateActivityPage(){
        return "createActivity";
    }

    @GetMapping("/activityDetail")
    public String getActivityDetailPage(){
        return "activityDetail";
    }

}

