package nl.cerios.cerioscoop.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BuyTicketsPage {
	private WebDriver driver;
	
	@FindBy(id = "movie-title")
	private WebElement movieTitle;
	
	@FindBy(id = "room-name")
	private WebElement roomName;
	
	@FindBy(id = "reserved_places")
	private WebElement reservedPlaces;
	
	@FindBy(id="buybutton")
	private WebElement buyButton;
	
	@FindBy(id="navbar-logout")
	private WebElement logOutButton;
	
	
	public BuyTicketsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public String getMovieTitleFromBuyTicketsPage() {
		 return movieTitle.getText();
	}
	
	public String getRoomNameFromBuyTicketsPage() {
		 return roomName.getText();
	}
	
	public void enterNumberOfTickets(String numberOfTickets){
		reservedPlaces.sendKeys(numberOfTickets);
	}
	
	public PaymentTicketsPage clickOnTheBuyButton(){
		buyButton.click();
		return new PaymentTicketsPage(driver);
	}
	
	public void clickOnTheLogOutButton(){
		logOutButton.click();
	}
	
}
