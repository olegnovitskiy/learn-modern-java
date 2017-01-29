package org.store.util;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	
	public static LocalDate strToDate(String text) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
		return LocalDate.parse(text);
	}

	public static int getYear(Date publish) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(publish);
		
		return calendar.get(Calendar.YEAR);
	}

}
