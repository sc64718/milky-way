package com.milkyway.utils;
 
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

public class DateUtils {
	
	 public static Timestamp getCurrentJavaSqlTimestamp() {
		 LocalTime localTime =  LocalTime.now(ZoneId.of("Asia/Kolkata"));
		return localTime == null ? null : Timestamp.valueOf(
			         localTime.atDate(LocalDate.now()));

			   }
	 
}
