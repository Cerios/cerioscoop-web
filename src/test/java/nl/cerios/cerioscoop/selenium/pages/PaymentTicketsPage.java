package nl.cerios.cerioscoop.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentTicketsPage {
	private WebDriver driver;
	
	@FindBy(id="navbar-logout")
	private WebElement logOutButton;
	
	public PaymentTicketsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String getPageTitle(){
		return driver.getTitle();
	}
	
	public IndexPage clickOnTheLogOutButton(){
		logOutButton.click();
		return new IndexPage(driver);
	}
}
