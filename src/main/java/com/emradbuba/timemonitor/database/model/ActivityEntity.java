package com.emradbuba.timemonitor.database.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ACTIVITIES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ActivityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Note: for Oracle a sequence will be used
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String activityId;

    @Column(nullable = false)
    private String title;

    @Column
    private String jiraTask;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ActivityStatusDb status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ActivityTypeDb type;

    @Column(nullable = false)
    private LocalDateTime startTime;
    private LocalDateTime stopTime;

    private String description;
}
