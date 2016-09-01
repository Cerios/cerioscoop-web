package nl.cerios.cerioscoop.domain;

import java.math.BigInteger;

public final class Movie {
	
	private BigInteger movieId;
	private Category category;
	private String title;
	private int minutes;
	private int movieType;
	private String language;
	private String description;
	private String trailer;
	private String cover;

	Movie(final BigInteger movieId, final String title, final Category category, final int minutes, final int movieType, 
			final String language, final String description, final String trailer, final String cover) {
		this.movieId = movieId;
		this.title = title;
		this.category = category;
		this.minutes = minutes;
		this.movieType = movieType;
		this.language = language;
		this.description = description;
		this.trailer = trailer;
		this.cover = cover;
	}

	public BigInteger getMovieId() {
		return movieId;
	}
	
	public Category getCategory() {
		return category;
	}

	public String getTitle() {
		return title;
	}

	public int getMinutes() {
		return minutes;
	}

	public int getMovieType() {
		return movieType;
	}

	public String getLanguage() {
		return language;
	}

	public String getDescription() {
		return description;
	}

	public String getTrailer() {
		return trailer;
	}

	public String getCover() {
		return cover;
	}
}



