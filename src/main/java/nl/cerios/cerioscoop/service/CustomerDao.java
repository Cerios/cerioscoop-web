package nl.cerios.cerioscoop.service;

import java.util.List;

import javax.ejb.Remote;

import nl.cerios.cerioscoop.domain.Customer;
import nl.cerios.cerioscoop.domain.Show;
import nl.cerios.cerioscoop.domain.Transaction;

@Remote
public interface CustomerDao {
	//Standard CRUD 
	public List<Customer> getCustomers();
	public Customer getCustomer(int customerId);
	public void updateCustomer(Customer customer);
	public void deleteCustomer(Customer customer);
	
	//Specific CRUD
	public List<Transaction> getTransactionByUsername(String username);
	public void deleteCustomerByUsername(String username);
	public void updateChairsSold(int chairsSold, int showingId);
	public Show getShowByShowId(int show_id);
	public boolean isUniqueUser(String user);
	
}
