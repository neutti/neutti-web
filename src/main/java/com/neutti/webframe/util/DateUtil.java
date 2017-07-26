package com.neutti.webframe.util;

import com.ibm.icu.util.ChineseCalendar;
import com.neutti.webframe.core.bean.CalenderVO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DateUtil {
	public static String convertDateToString(Date date) {
	    if(date == null) return null;
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 M월 d일, h시 m분 s초");
	    return formatter.format(date);
	}
	public static String convertDateToString(Date date, String format) {
	    if(date == null) return null;
	    SimpleDateFormat formatter = new SimpleDateFormat(format);
	    return formatter.format(date);
	}
	public static String convertDateToYear(Date date) {
	    if(date == null) return null;
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
	    return formatter.format(date);
	}

	public static String convertDateToMonth(Date date) {
	    if(date == null) return null;
	    SimpleDateFormat formatter = new SimpleDateFormat("MM");
	    return formatter.format(date);
	}


	public static String convertDateToDay(Date date) {
	    if(date == null) return null;
	    SimpleDateFormat formatter = new SimpleDateFormat("dd");
	    return formatter.format(date);
	}

	public static Date convertStringToDate(String date) {
	    if(date == null) return null;
	    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmm");
	    try {
			return format.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	public static Date convertStringToDate(String date, String formatStr) {
	    if(date == null) return null;
	    SimpleDateFormat format = new SimpleDateFormat(formatStr);
	    try {
			return format.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	/**
	 * 시간 내림
	 * @param date
	 * @return
	 */
	public static Date timeFloor(Date date) {
		String str = convertDateToString(date, "yyyy-MM-dd");
		return convertStringToDate(str,"yyyy-MM-dd");
	}
	/**
	 * 시간 올림
	 * @param date
	 * @return
	 */
	public static Date timeCeil(Date date) {
		String str = convertDateToString(date, "yyyy-MM-dd");
		return convertStringToDate(str + " 23:59:59","yyyy-MM-dd HH:mm:ss");
	}

	public static String getLunarDayStr(Date date) {
		ChineseCalendar chineseCal = new ChineseCalendar();
		chineseCal.setTimeInMillis(date.getTime());
		int month = chineseCal.get(ChineseCalendar.MONTH) + 1;
		int day = chineseCal.get(ChineseCalendar.DAY_OF_MONTH);
		return String.format("%02d", month) + "-" + String.format("%02d", day);
	}
	public static int getLunarYear(Date date) throws ParseException {
		ChineseCalendar chineseCal = new ChineseCalendar();
		chineseCal.setTimeInMillis(date.getTime());
		int year = chineseCal.get(ChineseCalendar.YEAR);
		return year + 1983;
	}
	public static int getLunarMonth(Date date) throws ParseException {
		ChineseCalendar chineseCal = new ChineseCalendar();
		chineseCal.setTimeInMillis(date.getTime());
		int month = chineseCal.get(ChineseCalendar.MONTH) + 1;
		return month;
	}
	public static int getLunarDay(Date date) throws ParseException {
		ChineseCalendar chineseCal = new ChineseCalendar();
		chineseCal.setTimeInMillis(date.getTime());
		int day = chineseCal.get(ChineseCalendar.DAY_OF_MONTH);
		return day;
	}
	public static Date addMonth(Date date, int i) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, i);
		return cal.getTime();
	}
	public static Date addDay(Date date, int i) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, i);
		return cal.getTime();
	}
	/**
	 * 달력에서 1일과 같은주에서 이전달 일요일 날짜
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date findPrevNearCalanderDate(Date date)  {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year =cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		String dateStr = String.format("%04d", year) + "-" + String.format("%02d", month) + "-01";
		try {
			Date prevDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
			long prevDateTime = prevDate.getTime();
			long day = 86400000;
			Calendar prevCal = Calendar.getInstance();
			prevCal.setTime(prevDate);
			int week = prevCal.get(Calendar.DAY_OF_WEEK);
			switch (week) {
			case Calendar.MONDAY:
				prevDateTime = prevDateTime - (1 * day);
				break;
			case Calendar.TUESDAY:
				prevDateTime = prevDateTime - (2 * day);
				break;
			case Calendar.WEDNESDAY:
				prevDateTime = prevDateTime - (3 * day);
				break;
			case Calendar.THURSDAY:
				prevDateTime = prevDateTime - (4 * day);
				break;
			case Calendar.FRIDAY:
				prevDateTime = prevDateTime - (5 * day);
				break;
			case Calendar.SATURDAY:
				prevDateTime = prevDateTime - (6 * day);
				break;
			case Calendar.SUNDAY:
				prevDateTime = prevDateTime - (7 * day);
				break;
			default:
				prevDateTime = prevDateTime - (7 * day);
				break;
			}
			return new Date(prevDateTime);
		} catch (ParseException e) {
			return new Date(0);
		}

	}
	/**
	 * 달력에서 마지막일과 같은주(경우에따라 다음주, 6주 기준)에서 다음달 토요일 날짜
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date findNextNearCalanderDate(Date date) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year =cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int dayofmonth =cal.get(Calendar.DAY_OF_MONTH);
		String dateStr = String.format("%04d", year) + "-" + String.format("%02d", month) + "-" + cal.getActualMaximum(Calendar.DATE);
		Date prevDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
		long prevDateTime = prevDate.getTime();
		long day = 86400000;
		Calendar prevCal = Calendar.getInstance();
		prevCal.setTime(prevDate);
		int week = prevCal.get(Calendar.DAY_OF_WEEK);
		switch (week) {
		case Calendar.MONDAY:
			if(dayofmonth == 31){
				prevDateTime = prevDateTime + (6 * day);
			}else{
				prevDateTime = prevDateTime + ((6+7) * day);
			}
			break;
		case Calendar.TUESDAY:
			prevDateTime = prevDateTime + ((5+7) * day);
			break;
		case Calendar.WEDNESDAY:
			prevDateTime = prevDateTime + ((4+7) * day);
			break;
		case Calendar.THURSDAY:
			prevDateTime = prevDateTime + ((3+7) * day);
			break;
		case Calendar.FRIDAY:
			prevDateTime = prevDateTime + ((2+7) * day);
			break;
		case Calendar.SATURDAY:
			prevDateTime = prevDateTime + (1 * day);
			break;
		case Calendar.SUNDAY:
			if(dayofmonth == 30 || dayofmonth == 31){
				prevDateTime = prevDateTime + (0 * day);
			}else{
				prevDateTime = prevDateTime + ((0+7) * day);
			}
			break;
		default:
			prevDateTime = prevDateTime + (1 * day);
			break;
		}
		return new Date(prevDateTime);
	}

	public static List<CalenderVO> getOneMonthRangeDateArray(Date date)  {
		Calendar orignal = Calendar.getInstance();
		orignal.setTime(date);
		int actualMaximumDay = orignal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//
		long day = 86400000;
		Date startDate = addDay(DateUtil.findPrevNearCalanderDate(date), -6);
		Date endDate = addDay(new Date(startDate.getTime() + (day * 42L)),7); //7*6 격자 범위
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		long time = startDate.getTime();
		long endTime = endDate.getTime();
		List<CalenderVO> dateList = new ArrayList<CalenderVO>();
		//대상날짜가 현재 월에 포함되는지 여부
		Boolean enterCurrentMonth = null;
		while(time < endTime){
			Calendar current = Calendar.getInstance();
			current.setTimeInMillis(time);
			int dayOfMonth = current.get(Calendar.DAY_OF_MONTH);
			if(enterCurrentMonth == null && dayOfMonth == 1){
				enterCurrentMonth = true;
			}
			//
			CalenderVO vo = new CalenderVO();
			vo.setTime(time);
			vo.setDay(DateUtil.convertDateToString(new Date(time), "d"));
			vo.setDayStr(DateUtil.convertDateToString(new Date(time), "MM-dd"));
			vo.setDayOfWeekStr(DateUtil.convertDateToString(new Date(time), "E"));
			vo.setLunarDayStr(DateUtil.getLunarDayStr(new Date(time)));
			vo.setColor(enterCurrentMonth != null && enterCurrentMonth ? "#ffffff" : "#F2F2EA");
			vo.setCurrentMonth(enterCurrentMonth != null && enterCurrentMonth);
			dateList.add(vo);
			//
			if(enterCurrentMonth != null && enterCurrentMonth && dayOfMonth == actualMaximumDay){
				enterCurrentMonth = false;
			}
			time = time + day;
		}
		return dateList;
	}
	public static String getDayOfWeekStr(final int day) {
		switch (day) {
		case 1:
			return "Sunday";
		case 2:
			return "Monday";
		case 3:
			return "Tuesday";
		case 4:
			return "Wednesday";
		case 5:
			return "Thursday";
		case 6:
			return "Friday";
		case 7:
			return "Saturday";
		default:
			return null;
		}
	}
	public static long daysDiff(Date from, Date to) {
	    return daysDiff(from.getTime(), to.getTime());
	}

	public static long daysDiff(long from, long to) {
	    return Math.round( (to - from) / 86400000D ); // 1000 * 60 * 60 * 24
	}
}
