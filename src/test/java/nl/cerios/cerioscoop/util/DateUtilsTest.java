package nl.cerios.cerioscoop.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTest {

	private static DateUtils DU = new DateUtils();
	
	@Test
	public void testGetDaysBetween() throws ParseException {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		String inputString1 = "23-01-1997";
		String inputString2 = "27-01-1997";
		Date date1 = myFormat.parse(inputString1);
		Date date2 = myFormat.parse(inputString2);
		
		long daysBetween = DU.getDaysBetween(date1, date2);
		Assert.assertEquals(4, daysBetween);
		daysBetween = DU.getDaysBetween(date2, date1);
		Assert.assertEquals(4, daysBetween);
		daysBetween = DU.getDaysBetween(date1, date1);
		Assert.assertEquals(0, daysBetween);
	}
	
	@Test
	public void testCalculateTime() {
        String calculateTime = DU.calculateTime(1500000);
		Assert.assertEquals("Year "+ 0 + " Week " + 2 + " Day " + 3 + " Hour " + 8 + " Minute " + 40 + " Seconds " + 0, calculateTime);
		calculateTime = DU.calculateTime(100000000);			//0,915343915343915
		Assert.assertEquals("Year "+ 3 + " Week " + 8 + " Day " + 2 + " Hour " + 9 + " Minute " + 46 + " Seconds " + 40, calculateTime);
		calculateTime = DU.calculateTime(167245806);
		Assert.assertEquals("Year "+ 5 + " Week " + 15 + " Day " + 3 + " Hour " + 17 + " Minute " + 10 + " Seconds " + 6, calculateTime);
		calculateTime = DU.calculateTime(2000000);
		Assert.assertEquals("Year "+ 0 + " Week " + 3 + " Day " + 2 + " Hour " + 3 + " Minute " + 33 + " Seconds " + 20, calculateTime);
		calculateTime = DU.calculateTime(5000000);
		Assert.assertEquals("Year "+ 0 + " Week " + 8 + " Day " + 1 + " Hour " + 20 + " Minute " + 53 + " Seconds " + 20, calculateTime);
		calculateTime = DU.calculateTime(22);
		Assert.assertEquals("Year "+ 0 + " Week " + 0 + " Day " + 0 + " Hour " + 0 + " Minute " + 0 + " Seconds " + 22, calculateTime);
		calculateTime = DU.calculateTime(50000000);
		Assert.assertEquals("Year "+ 1 + " Week " + 30 + " Day " + 4 + " Hour " + 16 + " Minute " + 53 + " Seconds " + 20, calculateTime);
		calculateTime = DU.calculateTime(90000000);
		Assert.assertEquals("Year "+ 2 + " Week " + 44 + " Day " + 5 + " Hour " + 16 + " Minute " + 0 + " Seconds " + 0, calculateTime);
    }
}
