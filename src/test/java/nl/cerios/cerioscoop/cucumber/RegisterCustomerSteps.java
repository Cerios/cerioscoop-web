package nl.cerios.cerioscoop.cucumber;

import org.junit.Assert;
import org.openqa.selenium.By;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nl.cerios.cerioscoop.service.GeneralService;
import nl.cerios.testutil.SeleniumTest;

public class RegisterCustomerSteps extends SeleniumTest {

	private GeneralService generalService = new GeneralService();
	private String userName = generalService.generateRandomUsername();
	
	@Given("^I am on the cerioscoop site$")
	public void navigateToCerioscoopSite() throws InterruptedException {
		getWebDriver().navigate().to(BASE_URL + "/index.jsp");
	}

	@When("^I navigate to register$")
	public void clickOnRegisterButton() throws InterruptedException {
		getWebDriver().findElement(By.id("navbar-login")).click();
		getWebDriver().findElement(By.id("register-button")).click();
	}

	@When("^I fill in the registerform with valid data and submit it$")
	public void fillInRegisterFormAndSubmit() throws InterruptedException {
		getWebDriver().findElement(By.id("firstname")).sendKeys("Selenium");
		getWebDriver().findElement(By.id("lastname")).sendKeys("SelLondon");
		getWebDriver().findElement(By.id("username")).sendKeys(userName);
		getWebDriver().findElement(By.id("password")).sendKeys("Sellondon");
		getWebDriver().findElement(By.id("password2")).sendKeys("Sellondon");
		getWebDriver().findElement(By.id("email")).sendKeys("selenium@london.com");
		getWebDriver().findElement(By.id("submit")).click();
	}

	@Then("^I check that the customer has been registered$")
	public void checkIfCustomerIsRegistered() throws InterruptedException {	
		Assert.assertEquals("Hello customer!", getWebDriver().getTitle());
		Assert.assertEquals("Welcome, your registry has been processed!\nHello "+userName+" (customer)!",
				getWebDriver().findElement(By.className("login-message")).getText());		
	}
}
