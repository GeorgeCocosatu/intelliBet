package com.intellibet.config;

import com.intellibet.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    @Autowired
    private EventService eventService;

    @Scheduled(fixedDelay = 5000)
    public void cronJob() {
        eventService.processPastEvents();
    }

}
