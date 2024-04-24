package com.simaom23.store.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimestampUtil {

    private TimestampUtil() {
    }

    // Convert Timestamp to String
    public static String convertToString(Timestamp timestamp) {
        if (timestamp == null) {
            throw new IllegalArgumentException("Timestamp and format must not be null or empty");
        }
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }

    // Convert String to Timestamp
    public static Timestamp convertToTimestamp(String dateString) {
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            return Timestamp.valueOf(localDateTime);
        } catch (Exception e) {
            throw new IllegalArgumentException("Date must be in the following format: YYYY-MM-DDThh:mm:ss");
        }
    }
}
