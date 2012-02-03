package com.teracode.android.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.teracode.android.common.exception.code.CommonErrorCode;

/**
 * @author Fernando Perez
 */
public final class DateUtil {

	/**
	 * Date format like yyyy-MM-ddTHH:mm:ss Z
	 */
	public static final SimpleDateFormat YYYYMMDDTHHMMSSZ_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZ");

	/**
	 * Date format like yyyy-MM-dd HH:mm:ss Z
	 */
	public static final SimpleDateFormat YYYYMMDDHHMMSSZ_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");

	/**
	 * Date format like yyyy-MM-dd HH:mm:ss
	 */
	public static final SimpleDateFormat YYYYMMDDHHMMSS_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * Date format like MM/dd/yyyy HH:mm
	 */
	public static final SimpleDateFormat MMDDYYYYHHMM_DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm");

	/**
	 * Date format like MM/dd/yyyy
	 */
	public static final SimpleDateFormat MMDDYYYY_DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

	/**
	 * Date format like MM-dd-yyyy
	 */
	public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("MM-dd-yyyy");

	/**
	 * Date format like yyyy-MM-dd
	 */
	public static final SimpleDateFormat YYYYMMDD_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Private constructor to avoid instance creations
	 */
	private DateUtil() {
		// Do noting...
	}

	/**
	 * @param dateString The {@link String} to transform to {@link Date}
	 * @return Date
	 */
	public static Date transform(String dateString) {
		return transform(dateString, DEFAULT_DATE_FORMAT);
	}

	/**
	 * @param dateString
	 * @param dateFormat
	 * @return Date
	 */
	public static Date transform(String dateString, SimpleDateFormat dateFormat) {
		Date date = null;
		if (!StringUtil.isEmpty(dateString)) {
			try {
				date = dateFormat.parse(dateString);
			} catch (ParseException e) {
				throw CommonErrorCode.INTERNAL_ERROR.newProgramException(e);
			}
		}

		return date;
	}

	/**
	 * @param dateString The {@link String} to transform to {@link Date}
	 * @param dateFormat
	 * @return Date
	 */
	public static Date transform(String dateString, String dateFormat) {
		Date date = null;
		if (!StringUtil.isEmpty(dateString)) {
			try {
				date = new SimpleDateFormat(dateFormat).parse(dateString);
			} catch (ParseException e) {
				throw CommonErrorCode.INTERNAL_ERROR.newProgramException(e);
			}
		}
		return date;
	}

	/**
	 * @param date
	 * @return String
	 */
	public static String unTransform(Date date) {
		return unTransform(date, DEFAULT_DATE_FORMAT);
	}

	/**
	 * @param date
	 * @param dateFormat
	 * @return String - date in string format
	 */
	public static String unTransform(Date date, SimpleDateFormat dateFormat) {
		return date != null ? dateFormat.format(date) : null;
	}

	/**
	 * @param date
	 * @param dateFormat
	 * @return String - date in string format
	 */
	public static String unTransform(Date date, String dateFormat) {
		return date != null ? new SimpleDateFormat(dateFormat).format(date) : null;
	}

	/**
	 * Creates a {@link Date} for the specified day
	 * 
	 * @param year The year
	 * @param monthOfYear The month number (starting on 0)
	 * @param dayOfMonth The day of the month
	 * @return The {@link Date}
	 */
	public static Date getDate(int year, int monthOfYear, int dayOfMonth) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, monthOfYear, dayOfMonth);
		return calendar.getTime();
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 
	 * @return
	 */
	public static Date now() {
		return new Date();
	}
}
