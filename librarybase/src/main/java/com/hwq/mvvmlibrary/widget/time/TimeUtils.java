package com.hwq.mvvmlibrary.widget.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    //天
    public static long onDay(long maxTime){

        return maxTime / (1000 * 24 * 60 * 60);

    }
    //小时
    public static long onHour(long maxTime){
        return  (maxTime - onDay(maxTime) * (1000 * 24 * 60 * 60)) / (1000 * 60 * 60);

    }
    //分钟
    public static long onMinute(long maxTime){

        return (maxTime - onDay(maxTime) * (1000 * 24 * 60 * 60) - onHour(maxTime) * (1000 * 60 * 60)) / (1000 * 60);

    }
    //秒
    public static long onSecond(long maxTime){

        return (maxTime - onDay(maxTime) * (1000 * 24 * 60 * 60) - onHour(maxTime) * (1000 * 60 * 60) - onMinute(maxTime) * (1000 * 60)) / 1000;

    }
    public static String getDateTimeFromMillisecond(Long millisecond) {
        if(millisecond==null||millisecond==0){
            return "--";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date = new Date(millisecond);
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }

    public static String getDateTimeFromMillisecond(Long millisecond, String format) {
        if(millisecond==null||millisecond==0){
            return "--";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = new Date(millisecond);
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }

    public static String getDateTimeFromMillisecond(Date date, String format) {
        if(date==null){
            return "--";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }

    public static String defaultTime(Date date) {
        if(date==null){
            return "--";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }
    public static String getMonthDay(String date) {
        if(date==null){
            return "--";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM月dd日");
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        if(null==parse){
            return "--";
        }
        return sdf1.format(parse);
    }
    public static String getDur(long millisecond) {
        long spaceSecond = (millisecond) / 1000;
        long nh = 1000 * 60 * 60;//一小时的毫秒数
        long nm = 1000 * 60;//一分钟的毫秒数
        long ns = 1000;//一秒钟的毫秒数

        long hour = millisecond  / nh;//计算差多少小时
        long min = millisecond  % nh / nm;//计算差多少分钟
        long sec = millisecond  % nh % nm / ns;//计算差多少秒
        return hour+":"+min+":"+sec;
    }

}
