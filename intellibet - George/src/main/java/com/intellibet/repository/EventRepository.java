package com.intellibet.repository;

import com.intellibet.model.Bet;
import com.intellibet.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("select e from Event e where e.dateTime <= :dateTime and e.outcome IS NULL")
    List<Event> findEventsBeforeDateTime(@Param("dateTime") LocalDateTime dateTime);

    @Query("select e from Event e where e.dateTime > :now")
    List<Event> findEventsAfterDateTime(@Param("now") LocalDateTime now);
}
