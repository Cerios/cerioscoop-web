package nl.cerios.cerioscoop.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerPage {
	private WebDriver driver;


	@FindBy(className = "login-message")
	private WebElement welcomeMessageParagraph;
	
	@FindBy(id = "transaction-button")
	private WebElement transactionButton;
	
	public CustomerPage(WebDriver driver) {
		// This call sets the WebElement fields.
		PageFactory.initElements(driver, this);
	}

	public String getWelcomeMessage() {
		return welcomeMessageParagraph.getText();
	}
	
	public TransactionPage navigateToTransactions(){
		transactionButton.click();
		return new TransactionPage(driver);
	}
}
