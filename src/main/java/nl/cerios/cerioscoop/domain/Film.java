package nl.cerios.cerioscoop.domain;

public class Film {
	private int filmId;
	private String name;
	private int minutes;
	private int movieType;
	private String language;

	// TODO this constructor is never used, do we need it?
	public Film() {

	}

	public Film(final int filmId, final String name, final int minutes, final int movieType, final String language) {
		this.filmId = filmId;
		this.name = name;
		this.minutes = minutes;
		this.movieType = movieType;
		this.language = language;
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(final int filmId) {
		this.filmId = filmId;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(final int minutes) {
		this.minutes = minutes;
	}

	public int getType() {
		return movieType;
	}

	public void setType(final int movieType) {
		this.movieType = movieType;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(final String language) {
		this.language = language;
	}
}
