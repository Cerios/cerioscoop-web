package nl.cerios.cerioscoop.service;

public class FilmNotFoundException extends Exception {
	
	public FilmNotFoundException(final String message) {
		super(message);
	}

	public FilmNotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
