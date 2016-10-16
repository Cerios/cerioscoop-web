package nl.cerios.cerioscoop.selenium;

import org.junit.Assert;
import org.junit.Test;

import nl.cerios.cerioscoop.selenium.pages.CustomerPage;
import nl.cerios.cerioscoop.selenium.pages.IndexPage;
import nl.cerios.testutil.SeleniumTest;

public class LoginTest extends SeleniumTest {
	
	@Test
	public void shouldLoginCustomerSuccessfully() {
	    CustomerPage customerPage = new IndexPage(getWebDriver())
	      .loginToCustomerPage("ron","SuperRon");
	    
	    Assert.assertNotNull(customerPage);
	    Assert.assertEquals("Welcome, you have been logged in successfully\nHello ron (customer)!", customerPage.getWelcomeMessage());
	  }
}
