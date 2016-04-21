package nl.cerios.cerioscoop.service;

public class VoorstellingServiceException extends RuntimeException {

	public VoorstellingServiceException(String message) {
		super(message);
	}

	public VoorstellingServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
