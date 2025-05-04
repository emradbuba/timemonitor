package com.emradbuba.timemonitor.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DatabaseService {

    private final ActivityRepository activityRepository;


}
