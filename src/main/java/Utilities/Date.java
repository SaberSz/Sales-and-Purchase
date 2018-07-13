/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author rudolf
 */
public class Date {

    public static String Date() {
        java.util.Date javaDate = new java.util.Date();
        long javaTime = javaDate.getTime();
        java.sql.Date sqlDate = new java.sql.Date(javaTime);
        System.out.println(" DATE : " + sqlDate.toString());
        return sqlDate.toString();
    }
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public static String nextDate(int days) {

        java.util.Date currentDate = new java.util.Date();
        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        // manipulate date
        c.add(Calendar.DATE, days); //same with c.add(Calendar.DAY_OF_MONTH, 1);
        // convert calendar to date
        java.util.Date currentDatePlusOne = c.getTime();
        java.util.Date currentDate1 = new java.util.Date();
        return dateFormat.format(currentDatePlusOne);

    }

    public static long beforeOrAfter(String previousDate) throws ParseException {

        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date prev = formatter1.parse(previousDate);
        java.util.Date current = new java.util.Date();

        if (prev.before(current)) {
            long diff = current.getTime() - prev.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);
            return diffDays;
        } else {
            //prev is after current
            return 0;
        }

    }

}
