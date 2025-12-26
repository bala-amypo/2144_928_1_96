package com.example.demo.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DateRangeUtil {

    public static List<LocalDate> daysBetween(LocalDate start, LocalDate end) {
        if (start.isAfter(end)) {
            return new ArrayList<>();
        }
        
        return Stream.iterate(start, date -> date.plusDays(1))
                     .limit(java.time.temporal.ChronoUnit.DAYS.between(start, end) + 1)
                     .collect(Collectors.toList());
    }
}