package com.kingkate.busfind.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Helper {
	public static int getCurrentTime() {
		return (int) (System.currentTimeMillis() / 1000);
	}

	public static int getCurrentDate() {
		return getDate(getCurrentTime());
	}

	public static int getCurrentMonth() {
		return getMonth((int) (System.currentTimeMillis() / 1000));
	}

	public static int getMonth(int time) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis((long) time * 1000);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1, 0, 0, 0);
		return (int) (c.getTimeInMillis() / 1000);
	}

	public static int addMonth(int time, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis((long) time * 1000);
		c.add(Calendar.MONTH, amount);
		return (int) (c.getTimeInMillis() / 1000);
	}

	public static int addDay(int time, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis((long) time * 1000);
		c.add(Calendar.DATE, amount);
		return (int) (c.getTimeInMillis() / 1000);
	}

	public static int getDate(int time) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis((long) time * 1000);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 0, 0, 0);

		return (int) (c.getTimeInMillis() / 1000);
	}

	public static String getFormatDate(Integer time, String format) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis((long) time * 1000);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 0, 0, 0);
		if (isEmpty(format))
			format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(c.getTime());
	}

	public static int toUnixTime(String stringDate) {
		Date date = new Date();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = sdf.parse(stringDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return toUnixTime(date);
	}
	
	
	public static int toUnixTime(Date date) {
		return (int) (date.getTime() / 1000);
	}
	
	public static int getFirstDayTime(int time) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis((long) time * 1000);
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return (int) (c.getTimeInMillis() / 1000);
	}

	public static int getLastDayTime(int time) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis((long) time * 1000);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return (int) (c.getTimeInMillis() / 1000);
	}

	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim()))
			return true;
		return false;
	}

	public static  <T extends  Object>  String listToString(List<T> list){
		StringBuilder sb=new StringBuilder();
		for(T item : list){
			if(sb.length()==0) {
				sb.append(item.toString());
			}
			else{
				sb.append(","+item.toString());
			}
		}
		return sb.toString();
	}


	public static   String listToString(String[] list){
		StringBuilder sb=new StringBuilder();
		for(String item : list){
			if(sb.length()==0) {
				sb.append(item);
			}
			else{
				sb.append(","+item);
			}
		}
		return sb.toString();
	}
	
	
	
}
