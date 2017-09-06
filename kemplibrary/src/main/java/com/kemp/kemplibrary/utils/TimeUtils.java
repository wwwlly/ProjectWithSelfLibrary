package com.kemp.kemplibrary.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by wangkp on 2017/7/10.
 */

public final class TimeUtils {

    public static String getCurrentTime() {
        return formatDate(new Date());
    }

    public static String formatDate(String pattern, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.CHINA);
        return simpleDateFormat.format(date);
    }

    public static String formatDate(Date date) {
        return formatDate("yyyy-MM-dd", date);
    }

    public static int getWeeks() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }
}
