package nl.cerios.cerioscoop.web;

public class ShowException extends RuntimeException {

	public ShowException(final String message) {
	    super(message);
	  }

	public ShowException(final String message, final Throwable cause) {
	    super(message, cause);
	  }
}
