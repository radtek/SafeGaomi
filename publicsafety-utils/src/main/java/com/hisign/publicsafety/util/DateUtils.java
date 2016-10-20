package com.hisign.publicsafety.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/***
 * 
 * Date 2013-8-26 下午9:20:43
 * @author yuanxiaolong
 * 日期格式转换的工具类
 */
public class DateUtils {

	public enum DateFormat{YM, YMD,YMDS, YMDHMS, YMDHMS_SHORT, YMDHM, HMS,YEAR, MONTH, DATE, 
		HOUR, MINUTE, SENONDS, 
		YMD_CHINESE,  YMDHMS_ALL_CHINESE, YMDHMS_CHINESE, HMS_CHINESE,
		WEEKDAY, YMDHMSW, YMDW, YMDHMSW_CHINESE, YMDW_CHINESE,YMDHMSW_ALL_CHINESE};
		
	public static final String YMDS = "yyyyMMdd";
	public static final String YM = "yyyyMM";
	public static final String YMD = "yyyy-MM-dd";
	public static final String YMD_CHINESE = "yyyy年MM月dd日";
	public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";
	public static final String YMDHMS_SHORT = "yyyyMMddHHmmss";
	public static final String YMDHM = "yyyy-MM-dd HH:mm";
	public static final String YMDHMCCP = "yyyy/MM/dd HH:mm";
	public static final String YMDHMS_CHINESE = "yyyy年MM月dd日 HH:mm:ss";
	public static final String YMDHMS_ALL_CHINESE = "yyyy年MM月dd日HH时mm分ss秒";
	public static final String HMS = "HH:mm:ss";
	public static final String HMS_CHINESE = "HH时mm分ss秒";
	public static final String YEAR = "yyyy";
	public static final String MONTH = "MM";
	public static final String DATE = "dd";
	public static final String HOUR = "HH";
	public static final String MINUTE = "mm";
	public static final String SENONDS = "ss";
	
	public static final String WEEKDAY = "E";
	public static final String YMDHMSW = "yyyy-MM-dd HH:mm:ss E";
	public static final String YMDW = "yyyy-MM-dd E";
	public static final String YMDHMSW_CHINESE = "yyyy年MM月dd日 HH:mm:ss E";
	public static final String YMDW_CHINESE = "yyyy年MM月dd日 E";
	public static final String YMDHMSW_ALL_CHINESE = "yyyy年MM月dd日HH时mm分ss秒 E";
	
	/**
	 * 
	 * @param date	
	 * @return	"yyyy-MM-dd"
	 */
	public static String parse2StringDate(Date date){
		SimpleDateFormat sdf  = new SimpleDateFormat();
		sdf.applyPattern(YMD);
		
		return sdf.format(date);
	}
	
	/**
	 * 
	 * @param date	
	 * @return	"yyyy-MM-dd hh:mm:ss"
	 */
	public static String parse2StringDatetime(Date date){
		SimpleDateFormat sdf  = new SimpleDateFormat();
		sdf.applyPattern(YMDHMS);
		
		return sdf.format(date);
	}
	
	/**
	 * 
	 * @param date
	 * @param pattern	日期格式化描述字符串
	 * @return
	 */
	public static String parse2String(Date date, String pattern){
		SimpleDateFormat sdf  = new SimpleDateFormat();
		sdf.applyPattern(pattern);
		
		return sdf.format(date);
	}
	
	/**
	 * 
	 * @param date
	 * @param format
	 * @return 格式化日期
	 * @throws Exception
	 */
	public static String parse2String(Date date, DateFormat format){
		SimpleDateFormat sdf  = new SimpleDateFormat();
		switch(format){
			case YM:sdf.applyPattern(YM);break;
			case YMD:sdf.applyPattern(YMD);break;
			case YMDS:sdf.applyPattern(YMDS);break;
			case YMDHMS:sdf.applyPattern(YMDHMS);break;
			case YMDHMS_SHORT:sdf.applyPattern(YMDHMS_SHORT);break;
			case HMS:sdf.applyPattern(HMS);break;
			case YEAR:sdf.applyPattern(YEAR);break;
			case MONTH:sdf.applyPattern(MONTH);break;
			case DATE:sdf.applyPattern(DATE);break;
			case HOUR:sdf.applyPattern(HOUR);break;
			case MINUTE:sdf.applyPattern(MINUTE);break;
			case SENONDS:sdf.applyPattern(SENONDS);break;
			case YMD_CHINESE:sdf.applyPattern(YMD_CHINESE);break;
			case YMDHMS_ALL_CHINESE:sdf.applyPattern(YMDHMS_ALL_CHINESE);break;
			case YMDHMS_CHINESE:sdf.applyPattern(YMDHMS_CHINESE);break;
			case WEEKDAY:sdf.applyPattern(WEEKDAY);break;
			case YMDHMSW:sdf.applyPattern(YMDHMSW);break;
			case YMDW:sdf.applyPattern(YMDW);break;
			case YMDHMSW_CHINESE:sdf.applyPattern(YMDHMSW_CHINESE);break;
			case YMDW_CHINESE:sdf.applyPattern(YMDW_CHINESE);break;
			case YMDHMSW_ALL_CHINESE:sdf.applyPattern(YMDHMSW_ALL_CHINESE);break;
			default:
				sdf.applyPattern(YMDHMS);break;
		}
		
		return sdf.format(date);
	}
	
	/**
	 * 
	 * @param str format yyyy-MM-dd
	 * @return
	 * @throws ParseException 
	 */
	public static Date string2Date(String str){
		SimpleDateFormat sdf = new SimpleDateFormat(YMD);
		
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			LogUtil.errStack2Log4j(e);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param str format yyyy-MM-dd hh:mm:ss
	 * @return
	 * @throws ParseException 
	 */
	public static Date string2Datetime(String str){
		SimpleDateFormat sdf = new SimpleDateFormat(YMDHMS);
		
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			LogUtil.errStack2Log4j(e);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param str
	 * @param pattern
	 * @return
	 * @throws ParseException 
	 */
	public static Date string2Datetime(String str, String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			LogUtil.errStack2Log4j(e);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 * @throws ParseException 
	 */
	public static Date string2Datetime(String str, String pattern, Locale alocale){
    	SimpleDateFormat sdf = new SimpleDateFormat(pattern, alocale);
    	try {
			return sdf.parse(str);
		} catch (ParseException e) {
			LogUtil.errStack2Log4j(e);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param timestamp 时间戳
	 * @param format	
	 * @return
	 */
	public static Date convet2Date(long timestamp){
		SimpleDateFormat sdf  = new SimpleDateFormat();
		
		return new Date(timestamp);
	}
	
	/**
	 * 
	 * @param timestamp 时间戳
	 * @param format	
	 * @return
	 */
	public static String convet2String(long timestamp, DateFormat format){
		SimpleDateFormat sdf  = new SimpleDateFormat();
		switch(format){
			case YMD:sdf.applyPattern(YMD);break;
			case YMDHMS:sdf.applyPattern(YMDHMS);break;
			case YMDHMS_SHORT:sdf.applyPattern(YMDHMS_SHORT);break;
			case YMDHM:sdf.applyPattern(YMDHM);break;
			case HMS:sdf.applyPattern(HMS);break;
			case YEAR:sdf.applyPattern(YEAR);break;
			case MONTH:sdf.applyPattern(MONTH);break;
			case DATE:sdf.applyPattern(DATE);break;
			case HOUR:sdf.applyPattern(HOUR);break;
			case MINUTE:sdf.applyPattern(MINUTE);break;
			case SENONDS:sdf.applyPattern(SENONDS);break;
			case YMD_CHINESE:sdf.applyPattern(YMD_CHINESE);break;
			case YMDHMS_ALL_CHINESE:sdf.applyPattern(YMDHMS_ALL_CHINESE);break;
			case YMDHMS_CHINESE:sdf.applyPattern(YMDHMS_CHINESE);break;
			case WEEKDAY:sdf.applyPattern(WEEKDAY);break;
			case YMDHMSW:sdf.applyPattern(YMDHMSW);break;
			case YMDW:sdf.applyPattern(YMDW);break;
			case YMDHMSW_CHINESE:sdf.applyPattern(YMDHMSW_CHINESE);break;
			case YMDW_CHINESE:sdf.applyPattern(YMDW_CHINESE);break;
			case YMDHMSW_ALL_CHINESE:sdf.applyPattern(YMDHMSW_ALL_CHINESE);break;
			default:
				sdf.applyPattern(YMDHMS);break;
		}
		
		return sdf.format(timestamp);
	}
	
	/**
	 * 获取当前日期时间
	 * @return
	 */
	public static String getCurrentTime(){
		
		return parse2String(new Date(), DateFormat.YMDHMS);
	}
	
	/**
	 * 获取当前日期
	 * @return
	 */
	public static String getCurrentDate(){
		
		return parse2String(new Date(), DateFormat.YMD);
	}

	/***
	 * 在日期之后 +" 23:59:59"
	 * @param str
	 * @return
	 */
	public static String dateOneDay(String str){
		return str +" 23:59:59";
	}
	
	/**
	 * 不可用，错误方法
	 * @param date
	 * @param flag 1 中文:星期一， 2 中文:礼拜一，3，英文 Mon, 4,英文Monday
	 * @return String
	 */
	@Deprecated
	private static String getWeekDay(Date date, int flag){
		String[] weekDayChinese_xq = new String[]{"星期日", "星期一","星期二","星期三","星期四","星期五","星期六"};
		String[] weekDayChinese_lb = new String[]{"礼拜天", "礼拜一","礼拜二","礼拜三","礼拜四","礼拜五","礼拜六"};
		String[] weekDayEnglish_short = new String[]{"Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat"};
		String[] weekDayEnglish_all = new String[]{"Sunday", "Monday", "Tuesday", "Wedsday", "Thursday", "Fridat", "Satday"};
		String weekDay = "";
		SimpleDateFormat sdf  = new SimpleDateFormat(WEEKDAY);
		String str = sdf.format(date);
		switch(flag){
		case 1:break;
		case 2:break;
		case 3:break;
		case 4:break;
		default:
			break;
		}
		
		return weekDay;
	}
	
	/**
	 * 获取nextday
	 * @param date
	 * @return
	 */
	public static Date getNextDay(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		return calendar.getTime();
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static Date getNextDay(String str){
		Date date = string2Date(str);
		return getNextDay(date);
	}
	
	/**
     * 获取date的前一天
     * @param date
     * @return
     */
    public static Date getPreviousDate(Date date){
    	Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -1);
        
        return cal.getTime();
    }
    
    /**
     * 计算日期d1与日期d2之间的差值
     * @param str1	日期时间1
     * @param str2	日期时间1	
     * @param pattern	日期时间格式
     * @return
     */
    public static long differDate(String str1, String str2, String pattern){
    	
    	return string2Datetime(str1, pattern).getTime() - string2Datetime(str2, pattern).getTime();
    }

    /**
     * 计算日期时间d1与日期时间d2之间的差值
     * @param d1
     * @param d2
     * @return
     */
    public static long differDate(Date d1, Date d2){
    	
    	return d1.getTime() - d2.getTime();
    }
    
}

