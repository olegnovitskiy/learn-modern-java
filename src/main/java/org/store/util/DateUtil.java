package org.store.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	
	public static Date strToDate(String text) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
		try {
			return dateFormat.parse(text);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static int getYear(Date publish) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(publish);
		
		return calendar.get(Calendar.YEAR);
	}

}
