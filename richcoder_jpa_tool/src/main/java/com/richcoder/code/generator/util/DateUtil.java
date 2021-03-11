package com.richcoder.code.generator.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO 线程不安全
 */
public class DateUtil {

    private static final SimpleDateFormat timeFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd");

    private static final SimpleDateFormat timeFormat2 = new SimpleDateFormat(
            "yyyyMMddHHmmss");

    private static final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

    /**
     * 日期转换为 yyyy-MM-dd 字符串
     *
     * @param date
     * @return
     */
    public static String date2String(Date date) {
        return dateFormat.format(date);
    }

    /**
     * 日期转换为 yyyy-MM-dd HH:mm:ss 字符串
     *
     * @param date
     * @return
     */
    public static String time2String(Date date) {
        return timeFormat.format(date);
    }

    /**
     * @param date
     * @return
     */
    public static String time2String2(Date date) {
        return timeFormat2.format(date);
    }

    /**
     * 字符串 yyyy-MM-dd HH:mm:ss 转换为 日期
     *
     * @param date
     * @return
     */
    public static Date String2time(String date) {

        if (StringUtils.isBlank(date)) {
            return null;
        }

        try {
            return timeFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串 yyyy-MM-dd 转换为 日期
     *
     * @param date
     * @return
     */
    public static Date String2date(String date) {

        if (StringUtils.isBlank(date)) {
            return null;
        }

        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串 MM/dd/yyyy 转换为 日期
     *
     * @param dateStr
     * @return
     */
    public static Date Str2date(String date) {

        if (StringUtils.isBlank(date)) {
            return null;
        }

        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 日期 转换为  MM/dd/yyyy
     *
     * @param date
     * @return
     */
    public static String date2Str(Date date) {
        return format.format(date);
    }

}
