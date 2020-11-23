package com.prlab.activityWeb.controller;

import com.prlab.activityWeb.model.Activity;
import com.prlab.activityWeb.model.HealthData;
import com.prlab.activityWeb.model.Member;
import com.prlab.activityWeb.model.User;
import com.prlab.activityWeb.model.repository.ActivityRepository;
import com.prlab.activityWeb.model.repository.HealthDataRepository;
import com.prlab.activityWeb.model.repository.MemberRepository;
import com.prlab.activityWeb.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class initData {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private HealthDataRepository healthDataRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String exampleInit(){
        userInit();
        activityInit();
        memberInit();
        healthDataInit();
        return "login";
    }

    private void userInit(){
        User user1 = new User();
        user1.setUsername("admin");
        user1.setPassword(bCryptPasswordEncoder.encode("admin"));
        user1.setDisplayName("RealAdmin");
        user1.setRole("admin");
        userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("user1");
        user2.setPassword(bCryptPasswordEncoder.encode("user1"));
        user2.setDisplayName("user1 is me");
        user2.setRole("user");
        userRepository.save(user2);
    }

    private void activityInit(){
        Activity activity1 = new Activity();
        activity1.setActivityName("台科火影跑");
        activity1.setActivityType("慢跑");
        activity1.setLocation("台科操場");
        activity1.setRule("中忍才能報名");
        User user = userRepository.findByUsername("admin");
        activity1.setOwner(user);
        activity1.setStartTime(new Date(120,10,20));
        activity1.setEndTime(new Date(120,10,25));
        activity1.setCreatedDate(new Date());
        activityRepository.save(activity1);

        Activity activity2 = new Activity();
        activity2.setActivityName("台科負重行走大賽");
        activity2.setActivityType("快走");
        activity2.setLocation("台科一餐");
        activity2.setRule("不歡迎弱雞");
        User user2 = userRepository.findByUsername("user1");
        activity2.setOwner(user2);
        activity2.setStartTime(new Date(120,10,22));
        activity2.setEndTime(new Date(120,10,27));
        activity2.setCreatedDate(new Date());
        activityRepository.save(activity2);
    }

    private void memberInit(){
        Activity activity1 = activityRepository.getOne(1);
        Activity activity2 = activityRepository.getOne(2);
        Member member1 = new Member();
        member1.setUsername("佐助");
        Member member2 = new Member();
        member2.setUsername("鳴人");
        memberRepository.save(member1);
        memberRepository.save(member2);
    }

    private void healthDataInit(){
        Member member1 = memberRepository.getOne(1);
        Member member2 = memberRepository.getOne(2);
        Activity activity1 = activityRepository.getOne(1);
        Activity activity2 = activityRepository.getOne(2);

        HealthData healthData1 = new HealthData();
        healthData1.setActivity(activity1);
        healthData1.setDistance(10);
        healthData1.setSteps(5000);
        healthData1.setPoints(10000);
        healthData1.setMember(member1);
        healthDataRepository.save(healthData1);


        HealthData healthData2 = new HealthData();
        healthData2.setActivity(activity1);
        healthData2.setDistance(50);
        healthData2.setSteps(10000);
        healthData2.setPoints(20000);
        healthData2.setMember(member2);
        healthDataRepository.save(healthData2);

        HealthData healthData3 = new HealthData();
        healthData3.setActivity(activity2);
        healthData3.setDistance(20);
        healthData3.setSteps(4000);
        healthData3.setPoints(1000);
        healthData3.setMember(member1);
        healthDataRepository.save(healthData3);

        HealthData healthData4 = new HealthData();
        healthData4.setActivity(activity2);
        healthData4.setDistance(400);
        healthData4.setSteps(50000);
        healthData4.setPoints(30000);
        healthData4.setMember(member2);
        healthDataRepository.save(healthData4);

    }
}
