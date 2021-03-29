package com.pantheonsorbonne.infocovid.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringUtil {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static int parseInt(String s) {
        return (!s.equals(" ")) ? Integer.parseInt(s) : 0;
    }

    public static double parseDouble(String s) {
        return (!s.equals(" ")) ? Double.parseDouble(s) : 0;
    }

    public static String csvInsertBlanks(String in, String separator) {
        String out = in;
        while (out.contains(separator + separator)) {
            out = out.replaceAll(separator + separator, separator + ' ' + separator);
        }
        return out;
    }

    public static LocalDate stringToLocalDate(String string) {
        return LocalDate.parse(string, formatter);
    }

    public static String localDateToString(LocalDate localDate) {
        return localDate.format(formatter);
    }

    public static List<LocalDate> getDateRange(LocalDate from, LocalDate to) {
        final int days = (int) from.until(to, ChronoUnit.DAYS);
        return Stream.iterate(from, d -> d.plusDays(1))
                .limit(days)
                .collect(Collectors.toList());
    }

}
