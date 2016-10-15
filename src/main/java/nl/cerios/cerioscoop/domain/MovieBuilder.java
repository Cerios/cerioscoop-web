package nl.cerios.cerioscoop.domain;

import java.math.BigInteger;

public final class MovieBuilder {
	
	private BigInteger movieId;
	private String title;
	private String description;

	public MovieBuilder withMovieId(final BigInteger value) {
		movieId = value;
		return this;
	}

	public MovieBuilder withMovieTitle(final String value) {
		title = value;
		return this;
	}

	public MovieBuilder withMovieDescription(final String value) {
		description = value;
		return this;
	}

	
	public Movie build() {
		return new Movie(movieId, title, description);
	}
}



