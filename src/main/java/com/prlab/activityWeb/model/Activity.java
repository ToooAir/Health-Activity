package com.prlab.activityWeb.model;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
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
