package nl.cerios.cerioscoop.cucumber;

import org.openqa.selenium.By;

import cucumber.api.java.en.And;
import nl.cerios.testutil.SeleniumTest;

public class IndexPageSteps extends SeleniumTest{
	
	@And("^Ik selecteer de voorstelling de Lion King die begint om tien uur$")
	public void selectShowTimeTenHoursOfMovieTheLionKing() throws InterruptedException {
		getWebDriver().findElement(By.id("showtimebyshowid6movieid6")).click();
	}
}
