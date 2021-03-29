package com.pantheonsorbonne.infocovid.util;

public class StringUtil {

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
}
