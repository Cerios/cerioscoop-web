package nl.cerios.cerioscoop.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTest {

	private static DateUtils dateUtils = new DateUtils();
	
	@Test
	public void testGetDaysBetween() throws ParseException {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		String inputString1 = "23-01-1997";
		String inputString2 = "27-01-1997";
		Date date1 = myFormat.parse(inputString1);
		Date date2 = myFormat.parse(inputString2);
		
		long daysBetween = dateUtils.getDaysBetween(date1, date2);
		Assert.assertEquals(4, daysBetween);
		daysBetween = dateUtils.getDaysBetween(date2, date1);
		Assert.assertEquals(4, daysBetween);
		daysBetween = dateUtils.getDaysBetween(date1, date1);
		Assert.assertEquals(0, daysBetween);
	}
	
	@Test
	public void testCalculateTime() {
        String calculateTime = dateUtils.calculateTime(1500000);
		Assert.assertEquals(0+"j "+ 2+"w "+ 3+"d "+ 8+"h "+ 40+"m "+ 0+"s ", calculateTime);
		calculateTime = dateUtils.calculateTime(100000000);			//0,915343915343915
		Assert.assertEquals(3+"j "+ 8+"w "+ 2+"d "+ 9+"h "+ 46+"m "+ 40+"s ", calculateTime);
		calculateTime = dateUtils.calculateTime(167245806);
		Assert.assertEquals(5+"j "+ 15+"w "+ 3+"d "+ 17+"h "+ 10+"m "+ 6+"s ", calculateTime);
		calculateTime = dateUtils.calculateTime(2000000);
		Assert.assertEquals(0+"j "+ 3+"w "+ 2+"d "+ 3+"h "+ 33+"m "+ 20+"s ", calculateTime);
		calculateTime = dateUtils.calculateTime(5000000);
		Assert.assertEquals(0+"j "+ 8+"w "+ 1+"d "+ 20+"h "+ 53+"m "+ 20+"s ", calculateTime);
		calculateTime = dateUtils.calculateTime(22);
		Assert.assertEquals(0+"j "+ 0+"w "+ 0+"d "+ 0+"h "+ 0+"m "+ 22+"s ", calculateTime);
		calculateTime = dateUtils.calculateTime(50000000);
		Assert.assertEquals(1+"j "+ 30+"w "+ 4+"d "+ 16+"h "+ 53+"m "+ 20+"s ", calculateTime);
		calculateTime = dateUtils.calculateTime(90000000);
		Assert.assertEquals(2+"j "+ 44+"w "+ 5+"d "+ 16+"h "+ 0+"m "+ 0+"s ", calculateTime);
	}	
}
