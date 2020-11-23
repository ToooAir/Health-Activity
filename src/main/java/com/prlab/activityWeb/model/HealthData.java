package com.prlab.activityWeb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Entity
@Table(name = "healthData")
public class HealthData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.LAZY)
    Member member;

    @ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.LAZY)
    Activity activity;

    Integer distance;

    Integer steps;

    Integer points;
}
