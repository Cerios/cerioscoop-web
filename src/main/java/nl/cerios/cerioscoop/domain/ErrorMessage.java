package nl.cerios.cerioscoop.domain;

public class ErrorMessage {
	
	private String firstnameError;
	private String lastnameError;
	private String usernameError;
	private String passwordError;
	private String emailError;
	

	public String getFirstnameError() {
		return firstnameError;
	}
	public void setFirstnameError(String firstnameError) {
		this.firstnameError = firstnameError;
	}
	public String getLastnameError() {
		return lastnameError;
	}
	public void setLastnameError(String lastnameError) {
		this.lastnameError = lastnameError;
	}
	public String getUsernameError() {
		return usernameError;
	}
	public void setUsernameError(String usernameError) {
		this.usernameError = usernameError;
	}
	public String getPasswordError() {
		return passwordError;
	}
	public void setPasswordError(String passwordError) {
		this.passwordError = passwordError;
	}
	public String getEmailError() {
		return emailError;
	}
	public void setEmailError(String emailError) {
		this.emailError = emailError;
	}

}
