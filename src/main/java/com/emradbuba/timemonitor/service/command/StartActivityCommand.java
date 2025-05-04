package com.emradbuba.timemonitor.service.command;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StartActivityCommand {

    private String title;
    private String description;
    private String jiraTask;
    private LocalDateTime declaredStartTime;
    private LocalDateTime commandCreationTime;
}