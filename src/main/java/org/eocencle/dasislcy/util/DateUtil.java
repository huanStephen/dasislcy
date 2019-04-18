package org.eocencle.dasislcy.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by chenchen on 2018/11/16.
 */
public class DateUtil {

    public static final String format1 = "yyyy-MM-dd";
    public static final String format2 = "yyyy-MM-dd HH:mm:ss";
    public static final String format3 = "yyyyMMddHHmmss";

    /**
     * 格式化时间
     */
    public static String format(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 时间字符串转Date
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static Date parse(String dateStr, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("日期转换错误 " + e);
        }
        return null;
    }

    /**
     * 今天日期
     */
    public static String getToday() {
        return format(new Date(), format1);
    }

    /**
     * 前一天日期
     */
    public static String previousDay() {
        return previousNDay(1);
    }

    /**
     * 几一天日期
     */
    public static String previousNDay(int n) {
        SimpleDateFormat sdf = new SimpleDateFormat(format1);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -n);
        Date preDay = c.getTime();
        String preDayStr = sdf.format(preDay);
        return preDayStr;
    }

}
