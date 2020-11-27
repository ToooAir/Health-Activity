package com.prlab.activityWeb.model.repository;

import com.prlab.activityWeb.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {
    List<Activity> findAllByOwner_id(Integer integer);
    List<Activity> findByActivityNameContaining(String name);
    List<Activity> findByOwner_idAndActivityNameContaining(Integer integer,String name);
}
