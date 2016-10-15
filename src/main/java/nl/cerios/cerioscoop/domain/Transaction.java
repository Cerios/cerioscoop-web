package nl.cerios.cerioscoop.domain;

public class Transaction {
	private int transactionId;
	private Customer customer;
	private Show show;
	private Room room;
	private Movie movie;
	private float totalPrice;
	private int reservedChairs;
	private String bankAccount;

	public int getTransactionId() {
		return transactionId;
	}
	
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getReservedChairs() {
		return reservedChairs;
	}

	public void setReservedChairs(int reservedChairs) {
		this.reservedChairs = reservedChairs;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

}
