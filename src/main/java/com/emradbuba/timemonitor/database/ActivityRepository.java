package com.emradbuba.timemonitor.database;

import com.emradbuba.timemonitor.database.model.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityEntity, Long> {

    public ActivityEntity findByActivityId(String activityId);
}
