package com.prlab.activityWeb.controller.service;

import com.prlab.activityWeb.model.HealthData;
import com.prlab.activityWeb.model.User;
import com.prlab.activityWeb.model.repository.HealthDataRepository;
import com.prlab.activityWeb.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/service/healthData")
public class HealthDataController {
    @Autowired
    private HealthDataRepository healthDataRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/delete/{Id}")
    public String deleteHealthData(@PathVariable(value="Id") String Id, Authentication authentication){
        Optional<HealthData> chosenHealthData = healthDataRepository.findById(Integer.parseInt(Id));
        User user = userRepository.findByUsername(authentication.getName());
        if (chosenHealthData.isPresent()) {
            if (chosenHealthData.get().getActivity().getOwner().getId() == user.getId() || user.getRole()=="admin") {
                Integer activityId = chosenHealthData.get().getActivity().getId();
                String url = "redirect:/activityDetail/" + activityId;
                healthDataRepository.delete(chosenHealthData.get());
                return url;
            }
        }
        return "error";
    }
}
