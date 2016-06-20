package nl.cerios.cerioscoop.service;

public class ShowingServiceException extends RuntimeException {

	public ShowingServiceException(final String message) {
		super(message);
	}

	public ShowingServiceException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
