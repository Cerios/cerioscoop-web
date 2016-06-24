package nl.cerios.cerioscoop.service;

public class ShowServiceException extends RuntimeException {

	public ShowServiceException(final String message) {
		super(message);
	}

	public ShowServiceException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
