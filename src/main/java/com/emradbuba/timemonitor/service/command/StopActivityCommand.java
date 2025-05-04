package com.emradbuba.timemonitor.service.command;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StopActivityCommand {

    private String activityId;
    private LocalDateTime declaredActivityStopTime;
    private LocalDateTime commandCreationTime;
}
