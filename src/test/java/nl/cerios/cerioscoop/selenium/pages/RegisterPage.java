package nl.cerios.cerioscoop.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	@FindBy(id = "firstname")
	private WebElement firstnameTextfield;
	
	@FindBy(id = "lastname")
	private WebElement lastnameTextfield;
	
	@FindBy(id = "username")
	private WebElement usernameTextfield;
	
	@FindBy(id = "password")
	private WebElement passwordTextfield;
	
	@FindBy(id = "password2")
	private WebElement passwordTextfield2;
	
	@FindBy(id = "email")
	private WebElement emailTextfield;
	
	@FindBy(id = "submit")
	private WebElement submitButton;
	
	public RegisterPage(WebDriver driver) {
		// This call sets the WebElement fields.
		PageFactory.initElements(driver, this);
	}
	
	public void fillInForm(){
		firstnameTextfield.sendKeys("Marcellino");
		lastnameTextfield.sendKeys("Groothuis");
		usernameTextfield.sendKeys("Manollo7G");
		passwordTextfield.sendKeys("origineel");
		passwordTextfield2.sendKeys("origineel");
		emailTextfield.sendKeys("mjg@gmail.com");
		submitButton.click();
	}
}
