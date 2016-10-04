package nl.cerios.cerioscoop.domain;

import java.math.BigInteger;
import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

import nl.cerios.cerioscoop.util.DateUtils;

public class ShowTest {

	@Test
	public void testInstantiateShow() throws ParseException {
		final Show show = new Show();
		
			show.setShowId(99);		
			Movie movie = new MovieBuilder().withMovieId(BigInteger.valueOf(10)).build();
			show.setMovie(movie);
			Room room = new Room();
			room.setRoomId(2);
			show.setRoom(room);
			show.setShowDate(DateUtils.convertUtilDateToSqlDate(DateUtils.toDate(DateUtils.toDateFormat("09-06-2016"))));
			show.setShowTime(DateUtils.convertUtilDateToSqlTime(DateUtils.toTime(DateUtils.toTimeFormat("20:00:00"))));
				
			Assert.assertNotNull(show);
			Assert.assertEquals(99, show.getShowId());
			Assert.assertEquals(10, show.getMovie().getMovieId().intValue());
			Assert.assertEquals(2, show.getRoom().getRoomId());
			Assert.assertEquals("2016-09-06", show.getShowDate().toString());  //The actual object is in java.sql.Date!
			Assert.assertEquals("20:00:00", show.getShowTime().toString());
	}

}
