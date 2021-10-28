package com.base.utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author xiongyang
 * @date 2020/6/4 16:49
 * @Description: 日期&时间工具类
 */
public class DateUtil {
    static String[] weekDays = {"7", "1", "2", "3", "4", "5", "6"};

    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat(
            "yyyy-MM-dd");
    public static final SimpleDateFormat DATE_CONCISE_DATE = new SimpleDateFormat(
            "yyyyMMdd");


    /**
     * 默认日期时间格式化 yyyy-MM-dd HH:mm:ss
     * @param timeInMillis
     * @return
     */
    public static String formatdateDefault(long timeInMillis){
        return getTime(timeInMillis,DEFAULT_DATE_FORMAT);
    }

    /**
     * 日期格式化 yyyy-MM-dd
     * @param timeInMillis
     * @return
     */
    public static String formatdateSimple(long timeInMillis){
        return getTime(timeInMillis,DATE_FORMAT_DATE);
    }

    /**
     * 日期格式化 yyyyMMdd
     * @param timeInMillis
     * @return
     */
    public static String formatdateConcise(long timeInMillis){
        return getTime(timeInMillis,DATE_CONCISE_DATE);
    }

    /**
     * 自定义日期格式化
     * @param timeInMillis
     * @return
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
    }

    /**
     * 日期是周几
     * @param date
     * @return
     */
    public static String getWeek(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar.get(Calendar.DAY_OF_WEEK)-1;
        if (i<=0){
            i = 0;
        }
        //String week = DateUtil.getWeek(new Date());
        //System.out.println(week);
        //System.out.println(weekDays[i]);
        return weekDays[i];
    }

    /**
     * 日期是否是今天
     * @param date
     * @return
     */
    public static int isToday(Date date){
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        int d1 = instance.get(Calendar.DAY_OF_YEAR);
        instance.setTime(date);
        int d2 = instance.get(Calendar.DAY_OF_YEAR);
        if (d1==d2){
            return 0;
        }else if (d1>d2){
            return 1;
        }else {
            return -1;
        }
    }

    /**
     * 日期是否是同一天
     * @param day1
     * @param day2
     * @return
     */
    public static boolean isSameDay(Date day1,Date day2){
        Calendar instance = Calendar.getInstance();
        instance.setTime(day1);
        int d1 = instance.get(Calendar.DAY_OF_YEAR);
        instance.setTime(day2);
        int d2 = instance.get(Calendar.DAY_OF_YEAR);
        if (d1==d2){
            return true;
        }
        return false;
    }

    /**
     * 获取当日时间戳
     * @param date
     * @return
     */
    public static long getDayString(Date date){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTimeInMillis();

    }

    /**
     * 获取日期的开始时间
     * @param date
     * @return
     */
    public static Date getTodayStartTime(Date date){
        Calendar todayStart = Calendar.getInstance();
        todayStart.setTime(date);
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    /**
     * 获取日期结束时间
     * @param date
     * @return
     */
    public static Date getTodayEndTime(Date date) {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.setTime(date);
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 0);
        return todayEnd.getTime();
    }

    /**
     * 获取今天开始时间
     * @return
     */
    public static Date getTodayStartTime(){
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    /**
     * 获取今天结束时间
     * @return
     */
    public static Date getTodayEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 0);
        return todayEnd.getTime();
    }


    /**
     * 获取这周日期的周一
     * @param date
     * @return
     */
    public static Date getThisWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }


    /**
     * 获取这周日期的周日
     * @param date
     * @return
     */
    public static Date getThisWeekSunday(Date date) {
        Calendar cal = Calendar.getInstance();
        Date thisWeekMonday = getThisWeekMonday(date);
        cal.setTime(thisWeekMonday);
        // 获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, 6);
        return cal.getTime();

    }


    /**
     * 获取这个月的第一天
     * @param date
     * @return
     */
    public static Date getMonthFirstDay(Date date){
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return cale.getTime();
    }

    /**
     * 获取这个月最后一天
     * @param date
     * @return
     */
    public static Date getMonthLastDay(Date date){
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return cale.getTime();
    }

    /**
     * 获取某年某月的第一天
     * @param year
     * @param month
     * @return
     */
    public static Date getFisrtDayOfMonth(int year,int month){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);

        return cal.getTime();
    }

    /**
     * 获取某年某月最后一天
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(int year,int month){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最小天数
        int firstDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);

        return cal.getTime();
    }

    /**
     * 获取某年第一天
     * @param year
     * @return
     */
    public static Date getFirstDayOfYear(int year){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, 0);
        //获取某月最小天数
        int firstDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);

        return cal.getTime();
    }

    /**
     * 获取某年最后一天
     * @param year
     * @return
     */
    public static Date getLastDayOfYear(int year){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, 11);
        //获取某月最小天数
        int firstDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);

        return cal.getTime();
    }

    public static int getWeekNum(Date date){

        int []arr = {7,1,2,3,4,5,6};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return arr[cal.get(Calendar.DAY_OF_WEEK)-1];
    }


}
