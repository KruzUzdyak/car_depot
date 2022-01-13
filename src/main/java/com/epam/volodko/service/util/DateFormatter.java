package com.epam.volodko.service.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateFormatter {

    private final static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public static Date format(String date) throws ParseException {
        return formatter.parse(date);
    }
}
