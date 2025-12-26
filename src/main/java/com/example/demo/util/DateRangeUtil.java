package com.example.demo.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateRangeUtil {
    public static long daysBetween(LocalDate a, LocalDate b) {
        return ChronoUnit.DAYS.between(a, b);
    }
}
