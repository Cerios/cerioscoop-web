package nl.cerios.cerioscoop.domain;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

public class CustomerTest {

	@Test
	public void testInstantiateCustomer() throws ParseException {
		final Customer customer = new Customer();
		
			customer.setCustomerId(100);		
			customer.setFirstName("Rob");
			customer.setLastName("Bosman");
			customer.setUsername("RB&B");
			customer.setPassword("rob123");
			customer.setEmail("rob@bosman.com");
				
			Assert.assertNotNull(customer);
			Assert.assertEquals(100, customer.getCustomerId());
			Assert.assertEquals("Rob", customer.getFirstName());
			Assert.assertEquals("Bosman", customer.getLastName());
			Assert.assertEquals("RB&B", customer.getUsername());
			Assert.assertEquals("rob123", customer.getPassword());
			Assert.assertEquals("rob@bosman.com", customer.getEmail());
	}
}


