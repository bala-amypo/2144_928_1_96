package com.example.demo.util;

import java.time.LocalDate;

public class DateRangeUtil {

    private DateRangeUtil() {
        // Utility class – prevent instantiation
    }

    /**
     * Checks whether two date ranges overlap.
     *
     * @param start1 start date of range 1
     * @param end1   end date of range 1
     * @param start2 start date of range 2
     * @param end2   end date of range 2
     * @return true if ranges overlap, false otherwise
     */
    public static boolean isOverlapping(
            LocalDate start1,
            LocalDate end1,
            LocalDate start2,
            LocalDate end2
    ) {
        if (start1 == null || end1 == null || start2 == null || end2 == null) {
            return false;
        }

        return !start1.isAfter(end2) && !start2.isAfter(end1);
    }
}
