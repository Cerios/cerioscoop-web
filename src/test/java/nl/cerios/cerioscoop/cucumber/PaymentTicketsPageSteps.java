package nl.cerios.cerioscoop.cucumber;

import org.junit.Assert;

import cucumber.api.java.en.Then;
import nl.cerios.testutil.SeleniumTest;

public class PaymentTicketsPageSteps extends SeleniumTest{
	
	@Then("^Check ik of het betaalscherm in het scherm staat$")
	public void checkIfPaymentScreenIsDisplayed() throws InterruptedException {
		Assert.assertNotNull(getWebDriver().getPageSource());
		Assert.assertEquals("Payment tickets", getWebDriver().getTitle());
	}

}
