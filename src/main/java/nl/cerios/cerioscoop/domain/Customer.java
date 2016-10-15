package nl.cerios.cerioscoop.domain;

public class Customer extends User{
	private int customerId;
	
	public Customer(){
	}
	
	public Customer(int customerId, String firstName, String lastName, String username, String password,
			String email) {
		super(firstName, lastName, username, password, email);
		this.customerId = customerId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
}
