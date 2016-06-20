package nl.cerios.cerioscoop.util;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * @author rsanders
 * 
 *  Handige links voor datum en tijd:
 *	http://stackoverflow.com/questions/4216745/java-string-to-date-conversion
 *  http://joda-time.sourceforge.net/quickstart.html
 */
public class DateUtils {
	private static final String DATE_FORMAT = "dd-MM-yyyy";
	private static final String DATE_FORMAT2 = "dd MMMMM";
	private static final String DATE_FORMAT3 = "yyyy-dd-MM";
	private static final String DATE_FORMAT_SQL = "yyyy-MM-dd";
	private static final String TIME_FORMAT = "HH:mm:ss";
	private static final String DATETIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
	
	public long getDaysBetween(final Date startDate, final Date endDate) {
		long diff = Math.abs(endDate.getTime() - startDate.getTime());
		long daysBetween = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		
		return daysBetween;
	}
	
	public long getSecondsBetween(final Date startDate, final Date endDate) {
		long diff = Math.abs(endDate.getTime() - startDate.getTime());
		long secondsBetween = TimeUnit.SECONDS.convert(diff, TimeUnit.MILLISECONDS);

		return secondsBetween;
	}
	
	/**
	 * Calculates the time from seconds into year, week, day, hour, minute and second.
	 * With bigger amounts of timeInSeconds there is a calculation failure at the day.
	 * It is a problem with rounding up the day at the write time!
	 * int dayRoundUp = (int) Math.ceil(day / 1.0);
	 * 
	 * 1 Minute = 60 Seconds
	 * 1 Hour = 3600 Seconds ( 60 * 60 )
	 * 1 Day = 86400 Seconds ( 24 * 3600 )
	 * 1 week = 604800 Seconds (7 *86400 )
	 * 1 Year = 31536000 Seconds (52,14285714285714 * 604800)
	 * 
	 * Let op je moet van sec naar jaar en jaar naar sec kunnen rekenen:
	 * 1,585489599188229	//50000000 seconden
	 * 18464000  //213,7037037037037 dagen uit het jaar  //30,52910052910053 weken
	 * 1 day = 0,0328549112 months
	 * 1 week = 0.0191653649 year  //820,67195767195767
	 * long day = ((seconds - (year*31536000+week*604800)) / 86400);
	 * 1 day = 0,142857143 week  //578,7037037037037 //320000   //4,703703698999986 days op basis van weeks
	 * 1 hour = 0,0416666667 days //13888,888 888 88889  //16,88888887536089 hours
	 * 1 min = 0,0166666667 uur //833333,333 333 3333 min  // 53,33333322673333
	 * 1 sec = 0.0166 666 667 min  //50 000 000 sec // 19,999999999958 sec
	 * 
	 * http://stackoverflow.com/questions/14497902/convert-seconds-to-w-d-h-m-format-in-java
	 * http://stackoverflow.com/questions/25903354/java-convert-seconds-to-minutes-hours-and-days
	 * http://stackoverflow.com/questions/4540684/java-round-up-any-number
	 * 
	 * Zo kan het ook:
	 * int weeks = (int) (TimeUnit.SECONDS.toDays(seconds) / 7);
	 * int days = (int) (TimeUnit.SECONDS.toDays(seconds) - 7 * weeks);
	 * long hours = TimeUnit.SECONDS.toHours(seconds) - TimeUnit.DAYS.toHours(days) - TimeUnit.DAYS.toHours(7*weeks);
	 * long minutes = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * 60);
	 * System.out.println(weeks+"w "+days+"d "+hours+"h "+minutes+"m");
	 * System.out.println((double)(167245806 % 604800) / 86400);
	 * System.out.println((int) Math.ceil(3.35 / 1.0));
	 * 
	 * @param timeInSeconds
	 * @return
	 */
	public String calculateTime(final long timeInSeconds) {
		final long oneMinuteInSec = 60;
		final long oneHourInSec = 3600;
		final long oneDayInSec = 86400;
		final int oneWeekInSec = 604800;	
		final int oneYearInSec = 31536000;
		
		final int year = (int) timeInSeconds / oneYearInSec;
		final int week = (int) ((timeInSeconds % oneYearInSec) / oneWeekInSec);  //Let op een int rond af op hele getallen!
		final long day = ((timeInSeconds % oneWeekInSec ) / oneDayInSec); 
		final long hours = (timeInSeconds % oneDayInSec ) / oneHourInSec;
		final long minute = ((timeInSeconds % oneDayInSec ) % oneHourInSec ) / oneMinuteInSec; 
		final long second = ((timeInSeconds % oneDayInSec ) % oneHourInSec ) % oneMinuteInSec;
		
		//System.out.println("In calc: Year " + year + " Week " + week + " Day " + day + " Hour " + hours + " Minute " + minute + " Seconds " + second);
        return year+"j "+ week+"w "+ day+"d "+ hours+"h "+ minute+"m "+ second+"s ";
    }
	
	public String convertCurrentSqlDateToString() {
		final DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
		String text = dateFormat.format(getCurrentSqlDate());
		System.out.println(text);
		return text;
	}
	/**
	 * http://www.java2s.com/Code/JavaAPI/java.sql/PreparedStatementsetTimeintparameterIndexTimex.htm
	 * 
	 * @return
	 */
	public java.sql.Date getCurrentSqlDate() {
		final java.util.Date date = Calendar.getInstance().getTime();
		final java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		return sqlDate;
	}
	public java.sql.Time getCurrentSqlTime() {
		final java.util.Date date = new java.util.Date();
		final Time sqlTime = new Time(date.getTime());
		return sqlTime;
	}
	public Date getCurrentDate() {
		return new Date();
	}
	
	/**
	 * It returns a date object converted into a string with format "dd-MM-yyyy".
	 * 
	 * @param datum
	 * @return String "dd-MM-yyyy"
	 */
	public String format(final Date datum) {
	    return datum != null ? new SimpleDateFormat(DATE_FORMAT).format(datum) : "onbekend";
	 }
	
	/**
	 * It returns a date object converted into a string with format "dd MMMMM".
	 * 
	 * @param datum
	 * @return String "dd MMMMM"
	 */
	public String format2(final Date datum) {
	    return datum != null ? new SimpleDateFormat(DATE_FORMAT2).format(datum) : "onbekend";
	 }
	
	/**
	 * It returns a date object converted into a string with format "yyyy-dd-MM".
	 * 
	 * @param date
	 * @return String "yyyy-dd-MM"
	 */
	public String format3(final Date date) {
	    return date != null ? new SimpleDateFormat(DATE_FORMAT3, Locale.FRANCE).format(date) : "onbekend";
	 }
	
	/**
	 * It returns a date object converted into a string with format "yyyy-MM-dd".
	 * 
	 * @param date
	 * @return String "yyyy-MM-dd"
	 */
	public String sqlDatabaseFormat(final Date date) {
	    return date != null ? new SimpleDateFormat(DATE_FORMAT_SQL, Locale.FRANCE).format(date) : "onbekend";
	 }
	
	/**
	 * It returns a time object converted into a string with format "HH:mm:ss".
	 * 
	 * @param time
	 * @return String "HH:mm:ss"
	 */
	public String timeFormat(final Date time) {
	    return time != null ? new SimpleDateFormat(TIME_FORMAT).format(time) : "onbekend";
	 }
	
	/**
	 * It returns a time object converted into a string with format "yyyy-MM-dd hh:mm:ss".
	 * 
	 * @param time
	 * @return String "yyyy-MM-dd hh:mm:ss"
	 */
	public String formatDatumEnTijd(final Date time) {
	    return time != null ? new SimpleDateFormat(DATETIME_FORMAT).format(time) : "onbekend";
	 }
	public String getDate() {
		return new SimpleDateFormat("d MMMM, HH:mm").format(new Date());
	}
  
}
