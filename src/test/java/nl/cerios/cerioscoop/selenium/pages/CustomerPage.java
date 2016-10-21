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
	
	@FindBy(id = "home-button")
	private WebElement cerioscoopButton;
	
	public CustomerPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public String getWelcomeMessage() {
		return welcomeMessageParagraph.getText();
	}
	
	public TransactionPage navigateToTransactions(){
		transactionButton.click();
		return new TransactionPage(driver);
	}
	
	public IndexPage navigateToHomePage(){
		cerioscoopButton.click();
		return new IndexPage(driver);
	}
}
