package com.neutti.webframe.core.bean;

import java.util.ArrayList;
import java.util.List;


/**
 * 차트
 * @author PIG
 *
 */
public class CalenderVO {
	private long time;
	private String dayOfYearStr;
	private String dayOfWeekStr;
	private String day;
	private String dayStr;
	private String lunarDayStr;
	private String color;
	private boolean currentMonth;
	private List<HolidayItemVO> holidayItems = new ArrayList<HolidayItemVO>();
	private List<ScheduleItemVO> scheduleItems = new ArrayList<ScheduleItemVO>();
	private long scheduleRows;
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getDayOfWeekStr() {
		return dayOfWeekStr;
	}
	public void setDayOfWeekStr(String dayOfWeekStr) {
		this.dayOfWeekStr = dayOfWeekStr;
	}
	public String getDayStr() {
		return dayStr;
	}
	public void setDayStr(String dayStr) {
		this.dayStr = dayStr;
	}
	public String getLunarDayStr() {
		return lunarDayStr;
	}
	public void setLunarDayStr(String lunarDayStr) {
		this.lunarDayStr = lunarDayStr;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public List<HolidayItemVO> getHolidayItems() {
		return holidayItems;
	}
	public void setHolidayItems(List<HolidayItemVO> holidayItem) {
		this.holidayItems = holidayItem;
	}
	public void addHolidayItems(HolidayItemVO holidayItem) {
		this.holidayItems.add(holidayItem);
	}
	public boolean isCurrentMonth() {
		return currentMonth;
	}
	public void setCurrentMonth(boolean currentMonth) {
		this.currentMonth = currentMonth;
	}
	public String getDayOfYearStr() {
		return dayOfYearStr;
	}
	public void setDayOfYearStr(String dayOfYearStr) {
		this.dayOfYearStr = dayOfYearStr;
	}
	public List<ScheduleItemVO> getScheduleItems() {
		return scheduleItems;
	}
	public void setScheduleItems(List<ScheduleItemVO> scheduleItems) {
		this.scheduleItems = scheduleItems;
	}
	public void addScheduleItems(ScheduleItemVO item) {
		this.scheduleItems.add(item);
	}
	public long getScheduleRows() {
		return scheduleRows;
	}
	public void setScheduleRows(long scheduleRows) {
		this.scheduleRows = scheduleRows;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
}
