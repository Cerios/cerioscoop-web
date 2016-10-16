package nl.cerios.cerioscoop.domain;

import java.math.BigInteger;

public final class Movie {

	private BigInteger movieId;
	private String title;
	private String description;

	Movie(final BigInteger movieId, final String title, final String movieDescription) {
		this.movieId = movieId;
		this.title = title;
		this.description = movieDescription;

	}

	public BigInteger getMovieId() {
		return movieId;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

}
