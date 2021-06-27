package com.grandblue.dingding.hashmap;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {

    public static String timeFormat(String hour){
        Integer value = Integer.valueOf(hour);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -value);
        Date date = calendar.getTime();
        sdf.format(date);
        return sdf.format(date);
    }

    public static void main(String[]args){
        System.out.println(timeFormat("1"));
    }
}
