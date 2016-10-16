package nl.cerios.cerioscoop.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TransactionPage {
	
	@FindBy(id = "movietitle")
	private WebElement movieTitle;
	
	@FindBy(id = "roomname")
	private WebElement roomName;
	
	@FindBy(id = "showdate")
	private WebElement showDate;
	
	@FindBy(id = "showtime")
	private WebElement showTime;
	
	@FindBy(id = "reservedchairs")
	private WebElement reservedChairs;
	
	public TransactionPage(WebDriver driver) {
		// This call sets the WebElement fields.
		PageFactory.initElements(driver, this);
	}
	
	public String getMovieTitle() {
		 return movieTitle.getText();
	}
	
	public String getRoomName() {
		 return roomName.getText();
	}
	
	public String getShowDate() {
		 return showDate.getText();
	}
	
	public String getShowTime() {
		 return showTime.getText();
	}
	
	public String getReservedChairs() {
		 return reservedChairs.getText();
	}
}
