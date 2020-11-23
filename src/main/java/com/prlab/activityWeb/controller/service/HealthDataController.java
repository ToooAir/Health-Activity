package com.prlab.activityWeb.controller.service;

import com.prlab.activityWeb.model.HealthData;
import com.prlab.activityWeb.model.repository.HealthDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/service/healthData")
public class HealthDataController {
    @Autowired
    private HealthDataRepository healthDataRepository;

    @GetMapping("/delete/{Id}")
    public String deleteHealthData(@PathVariable(value="Id") String Id, Authentication authentication){
        HealthData chosenHealthData = healthDataRepository.getOne(Integer.parseInt(Id));
        Integer activity = chosenHealthData.getActivity().getId();
        String url = "redirect:/activityDetail/" + activity;
        healthDataRepository.delete(chosenHealthData);
        return url;
    }
}
