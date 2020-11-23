package com.prlab.activityWeb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Entity
@Table(name = "activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String activityName;
    String location;
    String activityType;
    Date startTime;
    Date endTime;

    String rule;

    Date createdDate;

    @ManyToOne(cascade=CascadeType.DETACH)
    User owner;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "activity")
    List<HealthData> healthDatas;
}
