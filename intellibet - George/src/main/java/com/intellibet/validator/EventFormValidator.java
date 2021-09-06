package com.intellibet.validator;

import com.intellibet.dto.EventForm;
import com.intellibet.util.TimeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Service
public class EventFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(EventForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EventForm eventForm = (EventForm) target;


        if (Objects.isNull(eventForm.getPlayerA()) || StringUtils.isEmpty(eventForm.getPlayerA())) {
            errors.rejectValue("playerA", "eventForm.playerA.error");
        }
        if (Objects.isNull(eventForm.getPlayerB()) || StringUtils.isEmpty(eventForm.getPlayerB())) {
            errors.rejectValue("playerB", "eventForm.playerB.error");
        }
        if(eventForm.getPlayerA().equals(eventForm.getPlayerB())) {
            errors.reject("eventForm.global.samePlayers");
        }


        LocalDateTime localDateTime = TimeUtil.parseLocalDateTimeFrom(eventForm.getDate(), eventForm.getTime());
        if(localDateTime.isBefore(LocalDateTime.now())) {
            errors.rejectValue("date", "eventForm.date.past");
        }

        if (Objects.isNull(eventForm.getCategory()) || StringUtils.isEmpty(eventForm.getCategory())) {
            errors.rejectValue("category", "eventForm.category.error");
        }

        if (Objects.isNull(eventForm.getOddA()) || StringUtils.isEmpty(eventForm.getOddA())) {
            errors.rejectValue("oddA", "eventForm.global.invalidOdds");
        }
        if (Objects.isNull(eventForm.getOddB()) || StringUtils.isEmpty(eventForm.getOddB())) {
            errors.rejectValue("oddB","eventForm.global.invalidOdds");
        }
        if (Objects.isNull(eventForm.getOddX()) || StringUtils.isEmpty(eventForm.getOddX())) {
            errors.rejectValue("oddX","eventForm.global.invalidOdds");
        }


    }
}
