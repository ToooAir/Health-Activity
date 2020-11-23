package com.prlab.activityWeb.model.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestActivity {
    String activityName;
    String location;
    String activityType;
    String startTime;
    String endTime;

    String rule;
}

