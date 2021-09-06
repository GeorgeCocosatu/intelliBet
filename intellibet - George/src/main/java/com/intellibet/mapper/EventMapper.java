package com.intellibet.mapper;

import com.intellibet.dto.EventForm;
import com.intellibet.model.Event;
import com.intellibet.model.EventCategory;
import com.intellibet.util.TimeUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class EventMapper {


    public Event map(EventForm eventForm){
        Event event = new Event();
        event.setPlayerA(eventForm.getPlayerA());
        event.setPlayerB(eventForm.getPlayerB());
        event.setCategory(EventCategory.valueOf(eventForm.getCategory()));
        event.setOddA(Float.parseFloat(eventForm.getOddA()));
        event.setOddB(Float.parseFloat(eventForm.getOddB()));
        event.setOddX(Float.parseFloat(eventForm.getOddX()));

        LocalDateTime localDateTime = TimeUtil.parseLocalDateTimeFrom(eventForm.getDate(), eventForm.getTime());
        event.setDateTime(localDateTime);


        return event;

    }

    public EventForm map(Event event) {

        EventForm eventForm = new EventForm();
        eventForm.setPlayerA(event.getPlayerA());
        eventForm.setPlayerB(event.getPlayerB());
        eventForm.setCategory(event.getCategory().name());
        eventForm.setOddA(event.getOddA().toString());
        eventForm.setOddB(event.getOddB().toString());
        eventForm.setOddX(event.getOddX().toString());
        eventForm.setTime(event.getDateTime().toLocalTime().toString());
        eventForm.setDate(event.getDateTime().toLocalDate().toString());
        eventForm.setEventId(event.getId().toString());

        return eventForm;


    }
}
