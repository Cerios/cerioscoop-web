package nl.cerios.cerioscoop.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import nl.cerios.cerioscoop.selenium.pages.IndexPage;
import nl.cerios.cerioscoop.selenium.pages.RegisterPage;
import nl.cerios.testutil.SeleniumTest;

public class RegisterTest extends SeleniumTest {
	
	@Test
	public void shouldNavigateToRegisterPage() {
	    RegisterPage registerPage = new IndexPage(getWebDriver())
	  	      .navigateToRegisterPage();
	    
	    registerPage.fillInForm();
	    
	    String userName = "Manollo7G";
	    
	    Assert.assertNotNull(registerPage);
		final String loginMessage = getWebDriver().findElement(By.className("login-message")).getText().toString();
		Assert.assertEquals("Welcome, your registry has been processed!\nHello "+userName+" (customer)!", loginMessage);
	  }

}
