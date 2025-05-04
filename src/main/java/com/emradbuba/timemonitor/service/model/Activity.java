package com.emradbuba.timemonitor.service.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Activity {

    private String activityId;
    private String title;
    private String jiraTask;
    private ActivityStatus status;
    private ActivityType type;
    private LocalDateTime startTime;
    private LocalDateTime stopTime;
    private String description;
}
