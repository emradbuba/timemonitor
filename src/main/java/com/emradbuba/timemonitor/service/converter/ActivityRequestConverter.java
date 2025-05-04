package com.emradbuba.timemonitor.service.converter;

import com.emradbuba.timemonitor.controller.request.ActivityStartEventRequest;
import com.emradbuba.timemonitor.controller.request.ActivityStopEventRequest;
import com.emradbuba.timemonitor.service.command.StartActivityCommand;
import com.emradbuba.timemonitor.service.command.StopActivityCommand;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

// TODO: Implement input normalization logic on this level, so command contains a standarized use input for validation
@Component
public class ActivityRequestConverter {

    public StartActivityCommand toCommand(ActivityStartEventRequest activityStartEventRequest) {
        LocalDateTime now = LocalDateTime.now();
        return StartActivityCommand.builder()
                .title(activityStartEventRequest.getActivityTitle())
                .description(activityStartEventRequest.getActivityDescription())
                .jiraTask(activityStartEventRequest.getActivityJiraTask())
                .declaredStartTime(now)
                .commandCreationTime(now)
                .build();
    }

    public StopActivityCommand toCommand(ActivityStopEventRequest activityStopEventRequest) {
        return StopActivityCommand.builder()
                .activityId(activityStopEventRequest.getActivityId())
                .commandCreationTime(LocalDateTime.now())
                .build();
    }
}