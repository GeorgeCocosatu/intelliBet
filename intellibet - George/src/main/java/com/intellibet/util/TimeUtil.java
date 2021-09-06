package com.intellibet.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeUtil {

    public static LocalDateTime parseLocalDateTimeFrom(String date, String time){
        LocalDate localDate = LocalDate.parse(date);
        LocalTime localTime = LocalTime.parse(time);
        LocalDateTime localDateTime = localDate.atTime(localTime);
        return localDateTime;
    }
}
