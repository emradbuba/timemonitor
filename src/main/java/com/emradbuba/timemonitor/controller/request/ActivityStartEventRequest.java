package com.emradbuba.timemonitor.controller.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityStartEventRequest {

    private String activityTitle;
    private String activityDescription;
    private String activityJiraTask;
}