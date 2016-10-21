package nl.cerios.cerioscoop.cucumber;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import nl.cerios.testutil.SeleniumTest;

public class CommonPageSteps extends SeleniumTest{
	
	@Given("^Ik ben ingelogd$")
	public void navigateToHomePageAndLoginAsTestCustomer() throws InterruptedException {
		getWebDriver().navigate().to(BASE_URL + "/index.jsp");
		getWebDriver().findElement(By.id("navbar-login")).click();
		getWebDriver().findElement(By.id("loginUsername")).sendKeys("C");
		getWebDriver().findElement(By.id("loginPassword")).sendKeys("C");
		getWebDriver().findElement(By.id("login-button")).click();
		getWebDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
	
	@And("^Ik ben op de home pagina van de cerioscoop$")
	public void navigateToHomePage() throws InterruptedException {
		getWebDriver().navigate().to(BASE_URL + "/index.jsp");
	}
	
	@And("^Log ik uit$")
	public void clickLogOutButton() throws InterruptedException {
		getWebDriver().findElement(By.id("navbar-logout")).click();
	}
}
