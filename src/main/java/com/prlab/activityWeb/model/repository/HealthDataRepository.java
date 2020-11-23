package com.prlab.activityWeb.model.repository;

import com.prlab.activityWeb.model.HealthData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HealthDataRepository extends JpaRepository<HealthData, Integer> {
    List<HealthData> findAllByActivity_id(Integer integer);
}
