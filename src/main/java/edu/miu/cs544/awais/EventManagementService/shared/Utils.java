package edu.miu.cs544.awais.EventManagementService.shared;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static LocalDateTime convertStringToLocalDateTime(String date) {
        String dateWithTime = date + "T00:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return LocalDateTime.parse(dateWithTime, formatter);
    }
}
