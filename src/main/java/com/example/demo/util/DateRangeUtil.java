package com.example.demo.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DateRangeUtil {

    /**
     * Returns a list of all dates inclusive between the start and end dates.
     */
    public static List<LocalDate> daysBetween(LocalDate start, LocalDate end) {
        if (start.isAfter(end)) {
            // Can throw a business exception here, but for utility methods,
            // returning an empty list or handling gracefully is common.
            return new ArrayList<>();
        }
        
        return Stream.iterate(start, date -> date.plusDays(1))
                     .limit(java.time.temporal.ChronoUnit.DAYS.between(start, end) + 1)
                     .collect(Collectors.toList());
    }
}