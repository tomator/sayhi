package xyz.iseeyou.sayhi.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * author   bici
 * date     14/11/27.
 */

public class DateUtil {

    public static SimpleDateFormat format0 = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat format1 = new SimpleDateFormat(
            "yyyy-MM-dd");
    public static SimpleDateFormat format2 = new SimpleDateFormat(
            "H:mm:ss");
    public static SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public static SimpleDateFormat format4 = new SimpleDateFormat(
            "H:mm");

    public static SimpleDateFormat format5 = new SimpleDateFormat(
            "mm:ss");

    public static SimpleDateFormat format6 = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm");

    public static SimpleDateFormat format8 = new SimpleDateFormat(
            "MM-dd HH:mm");

    public static SimpleDateFormat format10 = new SimpleDateFormat(
            "MM月dd日 HH:mm");

    public static SimpleDateFormat format11 = new SimpleDateFormat(
            "yyyy/MM/dd");

    public static SimpleDateFormat format12 = new SimpleDateFormat(
            "yyyy.MM.dd");

    public static TimeZone timeZone = TimeZone.getTimeZone("GMT+8");

    public static void main(String[] args){
//        System.out.println(timeZone+ " , "+timeZone.getOffset(0));
//        System.out.println(format(100 * 1000 - timeZone.getOffset(0),2));

//        System.out.println(format(getMonthTime()));
    }


    public static String format(long timeStamp){
        return format(timeStamp,0);
    }

    public static String format(long timeStamp,int type){
        switch (type){
            case 0:
                return format0.format(new Date(timeStamp));
            case 1:
                return format1.format(new Date(timeStamp));
            case 2:
//                return format2.format(new Date(timeStamp - timeZone.getOffset(0)));
                return getHMS(timeStamp);
            case 3:
                return format3.format(new Date(timeStamp));
            case 4:
                return format4.format(new Date(timeStamp));
            case 5:
                return format5.format(new Date(timeStamp));
            case 6:
                return format6.format(new Date(timeStamp));
            case 7:
                return format2.format(new Date(timeStamp));
            case 8:
                return format8.format(new Date(timeStamp));
            case 9:
                return format0.format(new Date(timeStamp + timeZone.getOffset(0)));
            case 10:
                return format10.format(new Date(timeStamp));
            case 11:
                return format11.format(new Date(timeStamp));
            case 12:
                return format12.format(new Date(timeStamp));
            default:
                return format0.format(new Date(timeStamp));
        }
    }

    private static String getHMS(long timeStamp){
        long second = (timeStamp / 1000) % 60;
        long minute = (timeStamp / 1000 / 60) % 60;
        long hour = timeStamp / 1000 / 60 / 60;
        StringBuilder builder = new StringBuilder();
        if (hour > 0) {
            builder.append(hour);
            builder.append(":");
        }
        if(minute < 10){
            builder.append(0).append(minute);
        }else {
            builder.append(minute);
        }
        builder.append(":");
        if(second < 10){
            builder.append(0).append(second);
        }else{
            builder.append(second);
        }
        return builder.toString();
    }

    public static long parse(String timeStr){
        return parse(timeStr,0);
    }

    public static long parse(String timeStr,int type){
        try{
            switch (type){
                case 0:
                    return format0.parse(timeStr).getTime();
                case 1:
                    return format1.parse(timeStr).getTime();
                case 2:
                    return format2.parse(timeStr).getTime();
                case 3:
                    return format3.parse(timeStr).getTime();
                default:
                    return format0.parse(timeStr).getTime();
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public static long getMonthTime(){
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        calendar.set(year,month,1,0,0,0);
        return calendar.getTime().getTime();
    }

}
