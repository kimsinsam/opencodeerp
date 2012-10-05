/*
 * Created on 2006. 6. 12
 */
package open.accounting.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author xincub(xincub@playforum.net)
 */
public class DateUtils {
    private static SimpleCache cache = new SimpleCache(50);
    
    public static DateFormat getDateFormat(String pattern) {
        DateFormat df = (DateFormat) cache.get(pattern);
        if (null == df) {
            df = new SimpleDateFormat(pattern);
            cache.put(pattern, df);
        }
        return df;
    }
    
    /**
     * 문자열로 된 날짜 정보를 Date 형으로 리턴한다.
     * @param text 문자열로 된 날짜 정보
     * @param pattern 날짜 패턴
     * @param defaultDate ParsingException이 일어나는 경우 리턴할 Date
     */
    public static Date getDate(String text, String pattern, Date defaultDate) {
        DateFormat df = getDateFormat(pattern);
        Date result = null;
        try {
            result = df.parse(text);
        } catch (Exception ie) {
            result = defaultDate;
        }
        return result;
    }
    
    /**
     * 문자열로 된 날짜 정보를 Date 형으로 리턴한다.
     * @param text 문자열로 된 날짜 정보
     * @param pattern 날짜 패턴
     * @return ParsingException이 일어나는 경우 null
     */
    public static Date getDate(String text, String pattern) {
        return getDate(text, pattern, (Date) null);
    }
    
    /**
     * 문자열로 된 날짜 정보를 Date 형으로 리턴한다.
     * 패턴은 yyyy-MM-dd 이다.
     * @param text 문자열로 된 날짜 정보
     * @param defaultDate ParsingException이 일어나는 경우 리턴할 Date
     */
    public static Date getDate(String text, Date defaultDate) {
        return getDate(text, "yyyy-MM-dd", defaultDate);
    }
    
    /**
     * 문자열로 된 날짜 정보를 Date 형으로 리턴한다.
     * 패턴은 yyyy-MM-dd 이다.
     * @param text 문자열로 된 날짜 정보
     * @return ParsingException이 일어나는 경우 null
     */
    public static Date getDate(String text) {
        if (10 >= text.length()) {
            return getDate(text, "yyyy-MM-dd");
        } else {
            return getDate(text, "yyyy-MM-dd HH:mm:ss");
        }
    }
    
    /**
     * 날짜 정보를 Date 형으로 리턴한다.
     * 패턴은 yyyy-MM-dd 이다.
     * @param defaultDate ParsingException이 일어나는 경우 리턴할 Date
     */
    public static Date getDate(int year, int month, int day, Date defaultDate) {
        return getDate(year + "-" + month + "-" + day, defaultDate);
    }
    
    /**
     * 날짜 정보를 Date 형으로 리턴한다.
     * 패턴은 yyyy-MM-dd 이다.
     * @param defaultDate ParsingException이 일어나는 경우 리턴할 Date
     */
    public static Date getDate(String year, String month, String day, Date defaultDate) {
        return getDate(year + "-" + month + "-" + day, defaultDate);
    }
    
    /**
     * 날짜 정보를 Date 형으로 리턴한다.
     * 패턴은 yyyy-MM-dd 이다.
     * @return ParsingException이 일어나는 경우 null
     */
    public static Date getDate(int year, int month, int day) {
        return getDate(year + "-" + month + "-" + day);
    }
    
    /**
     * 날짜 정보를 Date 형으로 리턴한다.
     * 패턴은 yyyy-MM-dd 이다.
     * @return ParsingException이 일어나는 경우 null
     */
    public static Date getDate(String year, String month, String day) {
        return getDate(year + "-" + month + "-" + day);
    }
    
    /**
     * yyyy-MM-ddTHH:mm:ss.SSS 형식을 읽어들인다.
     */
    public static Date parseDateTime(String str) {
        DateFormat df = getDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        try {
            return df.parse(str);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * yyyy-MM-ddTHH:mm:ss.SSS 형식의 문자열을 얻는다.
     */
    public static String dateTimeToString(Date date) {
        DateFormat df = getDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        return df.format(date);
    }
    
    public static String dateToString(Date date) {
        DateFormat df = getDateFormat("yyyy-MM-dd");
        return df.format(date);
    }
    
    public static String format(String pattern, Date date) {
        DateFormat df = getDateFormat(pattern);
        return df.format(date);
    }
    
    public static Date getStartOfDate(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    
    public static Date getEndOfDate(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }
    
    public static Date getCurrentDay() {
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    
    /**
     * 
     * @param dateString
     * @return
     */
    public static Date changeDate(String dateString, String regExp) {
    	String[] dateArray = dateString.split("\\" + regExp);
    	GregorianCalendar cal = new GregorianCalendar();
    	cal.set(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]) -1, Integer.parseInt(dateArray[2]));
    	return cal.getTime();
    }
}
