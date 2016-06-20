package nl.cerios.cerioscoop.web;

public class ShowingException extends RuntimeException {

	public ShowingException(final String message) {
	    super(message);
	  }

	public ShowingException(final String message, final Throwable cause) {
	    super(message, cause);
	  }
}
