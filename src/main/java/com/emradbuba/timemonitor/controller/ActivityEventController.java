package com.emradbuba.timemonitor.controller;

import com.emradbuba.timemonitor.controller.request.ActivityStartEventRequest;
import com.emradbuba.timemonitor.controller.request.ActivityStopEventRequest;
import com.emradbuba.timemonitor.controller.response.TimeMonitorServiceResponse;
import com.emradbuba.timemonitor.service.ActivityEventService;
import com.emradbuba.timemonitor.service.command.StartActivityCommand;
import com.emradbuba.timemonitor.service.command.StopActivityCommand;
import com.emradbuba.timemonitor.service.converter.ActivityRequestConverter;
import com.emradbuba.timemonitor.service.model.Activity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/activity/event")
@RequiredArgsConstructor
public class ActivityEventController {

    private final ActivityEventService activityEventService;
    private final ActivityRequestConverter activityRequestConverter;

    @PostMapping("/start")
    public ResponseEntity<TimeMonitorServiceResponse<Activity>> startActivityEvent(@RequestBody ActivityStartEventRequest activityStartEventRequest) {

        StartActivityCommand startActivityCommand = activityRequestConverter.toCommand(activityStartEventRequest);
        Activity createdActivityModel = activityEventService.startNewActivity(startActivityCommand);

        TimeMonitorServiceResponse<Activity> serviceResponse = TimeMonitorServiceResponse.<Activity>builder()
                .withResponseBody(createdActivityModel)
                .withResponseTime(LocalDateTime.now())
                .withMessages(List.of("Successfully create a new activity"))
                .build();
        return new ResponseEntity<>(serviceResponse, HttpStatusCode.valueOf(OK.value()));
    }

    @PostMapping("/stop")
    public ResponseEntity<TimeMonitorServiceResponse<Activity>> stopActivityEvent(@RequestBody ActivityStopEventRequest activityStopEventRequest) {

        StopActivityCommand stopActivityCommand = activityRequestConverter.toCommand(activityStopEventRequest);
        Activity stoppedActivity = activityEventService.stopExistingActivity(stopActivityCommand);
        TimeMonitorServiceResponse<Activity> serviceResponse = TimeMonitorServiceResponse.<Activity>builder()
                .withResponseBody(stoppedActivity)
                .withResponseTime(LocalDateTime.now())
                .withMessages(List.of("Stop successful"))
                .build();
        return new ResponseEntity<>(serviceResponse, HttpStatus.valueOf(OK.value()));
    }
}

// NEXT STEPS:
// about generics...
// database - how to create an oracle one and add some liquibase changes?

// TODO: Validate input data - using annotations or some other mechanism for validations