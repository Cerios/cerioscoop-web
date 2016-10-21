package nl.cerios.cerioscoop.cucumber;

import org.junit.Assert;
import org.openqa.selenium.By;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nl.cerios.testutil.SeleniumTest;

public class CheckMoviePresentationSteps extends SeleniumTest{
	
	@Given("^Ik ben op de home pagina$")
	public void navigateToHomePage() throws InterruptedException {
		getWebDriver().navigate().to(BASE_URL + "/index.jsp");
	}
	
	@When("^Ik klik op ([^\"]*)$") //Dit is Regex voor het gebruik van input parameters in de feature file.
	public void ik_klik_op_thelionking_link(String link) throws InterruptedException {
	    getWebDriver().findElement(By.id(link)).click();
	}
	
	@Then("^Check ik of ik op ([^\"]*) scherm ben$")
	public void check_ik_of_ik_op_The_Lion_King_scherm_ben(String title) throws InterruptedException {
	    Assert.assertTrue(getWebDriver().getPageSource().contains(title));
	}
}
