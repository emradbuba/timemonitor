package com.emradbuba.timemonitor.service;

import com.emradbuba.timemonitor.database.ActivityRepository;
import com.emradbuba.timemonitor.database.model.ActivityEntity;
import com.emradbuba.timemonitor.database.model.ActivityStatusDb;
import com.emradbuba.timemonitor.database.model.ActivityTypeDb;
import com.emradbuba.timemonitor.service.command.StartActivityCommand;
import com.emradbuba.timemonitor.service.command.StopActivityCommand;
import com.emradbuba.timemonitor.service.model.Activity;
import com.emradbuba.timemonitor.service.model.ActivityStatus;
import com.emradbuba.timemonitor.service.model.ActivityType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ActivityEventService {

    private final ActivityRepository activityRepository;


    public Activity startNewActivity(StartActivityCommand command) {
        return startNewActivity(command, ActivityType.WORK);
    }

    public Activity startNewActivity(StartActivityCommand command, ActivityType activityType) {
        ActivityEntity newActivityEntity = new ActivityEntity();
        String generatedActivityId = UUID.randomUUID().toString();

        newActivityEntity.setActivityId(generatedActivityId);
        newActivityEntity.setTitle(command.getTitle());
        newActivityEntity.setJiraTask(command.getJiraTask());
        newActivityEntity.setStatus(ActivityStatusDb.STARTED);
        newActivityEntity.setType(toActivityTypeDb(activityType));
        newActivityEntity.setDescription(command.getDescription());
        newActivityEntity.setStartTime(command.getCommandCreationTime());
        newActivityEntity.setStopTime(null);

        activityRepository.save(newActivityEntity);
        ActivityEntity storedActivity = activityRepository.findByActivityId(generatedActivityId);

        return Activity.builder()
                .activityId(storedActivity.getActivityId())
                .title(storedActivity.getTitle())
                .jiraTask(storedActivity.getJiraTask())
                .type(toActivityType(storedActivity.getType()))
                .description(storedActivity.getDescription())
                .startTime(storedActivity.getStartTime())
                .stopTime(storedActivity.getStopTime())
                .status(ActivityStatus.valueOf(storedActivity.getStatus().name()))
                .build();
    }

    public Activity stopExistingActivity(StopActivityCommand command) {
        throw new UnsupportedOperationException("This operation is not yet implemented");
    }

    private ActivityTypeDb toActivityTypeDb(ActivityType activityType) {
        return switch (activityType) {
            case BREAK -> ActivityTypeDb.BREAK;
            case WORK -> ActivityTypeDb.WORK;
            case null -> throw new IllegalArgumentException("Activity type must not be null");
        };
    }

    private ActivityType toActivityType(ActivityTypeDb activityTypeDb) {
        return switch (activityTypeDb) {
            case BREAK -> ActivityType.BREAK;
            case WORK -> ActivityType.WORK;
            case null -> throw new IllegalArgumentException("Activity type (db) must not be null");
        };

    }
}
