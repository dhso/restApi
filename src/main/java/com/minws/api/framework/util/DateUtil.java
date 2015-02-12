/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.api.framework.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static final Integer node_year = Calendar.YEAR;
	public static final Integer node_month = Calendar.MONTH;
	public static final Integer node_date = Calendar.DATE;
	public static final Integer node_hour = Calendar.HOUR;
	public static final Integer node_minute = Calendar.MINUTE;
	public static final Integer node_second = Calendar.SECOND;

	/**
	 * 格式化输出日期时间
	 * 
	 * @param date
	 *            Date型时间
	 * @param style
	 *            设置时间格式 "yyyy-MM-dd hh:mm:ss"
	 * @return String类型
	 */
	public static String convertDate2String(Date date, String style) {
		SimpleDateFormat DF = new SimpleDateFormat(style);// 设置日期时间格式
		return DF.format(date);
	}

	/**
	 * 加减时间
	 * 
	 * @param date
	 *            Date型时间
	 * @param node
	 *            时间节点 Calendar.YEAR
	 * @param number
	 *            时间加减数量
	 * @return
	 */
	public static Date addDateNode(Date date, Integer node, Integer number) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(node, number);
		date = cal.getTime();
		return date;
	}

	/**
	 * 比较时间
	 * 
	 * @param date
	 *            大的Date
	 * @param date
	 *            小的Date
	 * @return
	 */
	public static boolean difDate(Date date1, Date date2, Boolean isEqual) {
		int difDate = date1.compareTo(date2);
		if (difDate > 0) {
			return true;
		} else if (difDate < 0) {
			return false;
		} else {
			if (isEqual) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * 将date转换成unix时间戳
	 * 
	 * @param date
	 *            Date
	 * @return
	 */
	public static String unixTimestamp(Date date) {
		return Long.toString(date.getTime());
	}

	/**
	 * 将unix时间戳转换成date
	 * 
	 * @param iTimestamp
	 * @return
	 */
	public static Date convertUnixTimestamp(String iTimestamp) {
		Long timestamp = Long.parseLong(iTimestamp);
		return new Date(timestamp);
	}
}