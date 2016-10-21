package nl.cerios.cerioscoop.cucumber;

import org.junit.Assert;
import org.openqa.selenium.By;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nl.cerios.testutil.SeleniumTest;

public class BuyTicketsPageSteps extends SeleniumTest{
		
	@When("^Vul ik het aantal tickets in$")
	public void enterTheNumberOfTickets() throws InterruptedException {
		getWebDriver().findElement(By.id("reserved_places")).sendKeys("3");
	}

	@And("^Druk ik op de knop koop$")
	public void clickBuyButton() throws InterruptedException {
		getWebDriver().findElement(By.id("buybutton")).click();
	}

	@When("^Vul ik het aantal tickets in dat groter is dan het maximale aantal tickets$")
	public void enterTheNumberOfTicketsThatExceedsTheMaximumNumberOfTickets() throws InterruptedException {
		getWebDriver().findElement(By.id("reserved_places")).sendKeys("55");
	}

	@When("^Vul ik het aantal tickets in dat kleiner is dan één ticket$")
	public void enterTheNumberOfTicketsSmallerThanOne() throws InterruptedException {
		getWebDriver().findElement(By.id("reserved_places")).sendKeys("0");
	}

	@When("^Vul ik het aantal tickets in dat het aantal beschikbare plaatsen overschrijd$")
	public void enterTheNumberOfTicketsThatExceedsTheAvailableChairs() throws InterruptedException {
		getWebDriver().findElement(By.id("reserved_places")).sendKeys("51");
	}
	
	/**
	 * Let op ik verwacht hier dat er geen "message" wordt getoond!
	 * 
	 * @param message
	 * @throws InterruptedException
	 */
	@Then("^Check ik of er een melding \"([^\"]*)\" verschijnt$")
	public void checkIfMessageIsDisplayed(String message) throws InterruptedException {
	    Assert.assertFalse(getWebDriver().getPageSource().contains(message));
	}
}
