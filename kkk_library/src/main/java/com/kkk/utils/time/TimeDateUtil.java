package com.kkk.utils.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by dayima on 16-11-15.
 * 时间处理工具类
 */

public class TimeDateUtil {
    /**timeToFormatTime("2015-01-22 14:32:56","yyyy-MM-dd HH:mm:ss","yyyy年MM月dd日 HH:mm");
     * 将给定的时间转换为想要格式的时间
     * @param time
     * @param oldDateFormat
     * @param dateFormat
     * @return
     */
    private static String timeToFormatTime(String time,String oldDateFormat,String dateFormat) {
        Long l = stringToTime(time,oldDateFormat);
        String d = timeToString(l+"",dateFormat);
        return d;
    }
    /**
     * 时间戳转换为想要格式的时间
     * @param time 时间戳
     * @param dateFormat 时间格式
     * @return
     */
    private static String timeToString(String time,String dateFormat) {
        SimpleDateFormat format =  new SimpleDateFormat(dateFormat);
        Long l =Long.parseLong(time);
        String d = format.format(l);
        return d;
    }
    /**
     * 字符串时间转换为时间戳
     * @param time 所要转换的时间
     * @param dateFormat 所传时间格式 例如:yyyy-MM-dd
     * @return
     */
    public static long stringToTime(String time,String dateFormat) {
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat(dateFormat);
        Date date= null;
        try {
            date = simpleDateFormat .parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long timeStemp = date.getTime();
        return timeStemp;
    }

    /**
     * @param time 时间戳精确到秒
     * @param tag 0判断是否为今天，1判断是否为当月，2判断是否为当年
     * @return -1表示比当前天小，0等于当天，1表示比当前天大
     */
    public static int isTodayOfMonthOfYear(long time,int tag){
        int day = -1;
        int month;
        int year;
        String formatTime = timeToString(String.valueOf(time),"yyyy-MM-dd HH:mm");
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(formatTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar current = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        today.set(Calendar.YEAR, current.get(Calendar.YEAR));
        today.set(Calendar.MONTH, current.get(Calendar.MONTH));
        today.set(Calendar.DAY_OF_MONTH,current.get(Calendar.DAY_OF_MONTH));
        //  Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
        today.set( Calendar.HOUR_OF_DAY, 0);
        today.set( Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        current.setTime(date);
        if (today.get(Calendar.YEAR)==current.get(Calendar.YEAR)){
            year = 0;
            if (today.get(Calendar.MONTH)==current.get(Calendar.MONTH)){
                month = 0;
                if (today.get(Calendar.DAY_OF_MONTH)==current.get(Calendar.DAY_OF_MONTH)){
                    day = 0;
                }else if (today.get(Calendar.DAY_OF_MONTH)>current.get(Calendar.DAY_OF_MONTH)){
                    day = -1;
                }else {
                    day = 1;
                }
            }else if (today.get(Calendar.MONTH)>current.get(Calendar.MONTH)){
                month = -1;
            }else {
                month = 1;
            }
        }else if (today.get(Calendar.YEAR)>current.get(Calendar.YEAR)){
            year = -1;
            day = -1;
            month = -1;
        }else {
            year = 1;
            day = 1;
            month = 1;
        }
        switch (tag){
            case 0:
                return day;
            case 1:
                return month;
            case 2:
                return year;
        }
        return day;
    }
}
