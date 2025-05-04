package com.emradbuba.timemonitor.controller.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityStopEventRequest {

    private String activityId;
}
