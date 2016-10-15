package nl.cerios.cerioscoop.domain;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;

public class MovieBuilderTest {

	@Test
	public void testInstantiateMovie() {
		final Movie movie = new MovieBuilder()
			.withMovieId(BigInteger.valueOf(1255))
			.withMovieTitle("kansloze titel")
			.withMovieDescription("bagger b-film")
			.build();

		Assert.assertNotNull(movie);
		Assert.assertEquals("kansloze titel", movie.getTitle());
		Assert.assertEquals("1255", movie.getMovieId().toString());
		Assert.assertEquals("bagger b-film", movie.getDescription());
	}
}