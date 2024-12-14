package com.fullStack.project.todolist.converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateConverter {

    public static String dateToString (Timestamp timestamp) {
        return timestamp.toLocalDateTime().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );
    }

    public static Timestamp stringToDate (String timestampString) {

        String[] dateStringSplit = timestampString.split(" ");

        String dateString = dateStringSplit[0];
        String timeString = dateStringSplit[1];
        String [] date = dateString.split("-");
        String [] time = timeString.split(":");

        return Timestamp.valueOf(LocalDateTime.of(
                Integer.parseInt(date[0]),
                Integer.parseInt(date[1]),
                Integer.parseInt(date[2]),
                Integer.parseInt(time[0]),
                Integer.parseInt(time[1]),
                Integer.parseInt(time[2]))
        );
    }
}
