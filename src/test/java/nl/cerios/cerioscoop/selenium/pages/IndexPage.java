package nl.cerios.cerioscoop.selenium.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexPage {
	private WebDriver driver;

	@FindBy(id = "navbar-login")
	private WebElement loginNavigationLink;
	
	@FindBy(id = "navbar-register")
	private WebElement registerNavigationLink;

	@FindBy(id = "loginUsername")
	private WebElement usernameTextfield;

	@FindBy(id = "loginPassword")
	private WebElement passwordTextfield;

	@FindBy(id = "login-button")
	private WebElement loginButton;
	
	@FindBy(id = "1") //Is movieId
	private WebElement theLegendOfTarzan;
	
	@FindBy(id = "2") //Is movieId
	private WebElement tarzanTheApeMan;
	
	@FindBy(id = "4") //Is movieId
	private WebElement weddingCrashers;
	
	@FindBy(id = "5") //Is movieId
	private WebElement bloodDiamond;
	
	@FindBy(id = "6") //Is movieId
	private WebElement theLionKing;
	
	@FindBy(id = "7") //Is movieId
	private WebElement snatch;

	// constructor
	public IndexPage(WebDriver driver) {
		this.driver = driver;
		driver.get("http://localhost:9080/cerioscoop-web");
		// This call sets the WebElement fields.
		PageFactory.initElements(driver, this);
	}

	// gedrag van de pagina
	public CustomerPage loginToCustomerPage(String username, String password) {
		loginNavigationLink.click();
		usernameTextfield.sendKeys(username);
		passwordTextfield.sendKeys(password);
		loginButton.click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		return new CustomerPage(driver);
	}
	
	public MoviePage navigateToMoviePresentation(String movieId){
		if (theLegendOfTarzan.isDisplayed() && movieId.equals("1")){
			theLegendOfTarzan.click();
		}else if(tarzanTheApeMan.isDisplayed() && movieId.equals("2")){
			tarzanTheApeMan.click();
			
		}else if(weddingCrashers.isDisplayed() && movieId.equals("4")){
			weddingCrashers.click();
			
		}else if(bloodDiamond.isDisplayed() && movieId.equals("5")){
			bloodDiamond.click();
			
		}else if(theLionKing.isDisplayed() && movieId.equals("6")){
			theLionKing.click();
			
		}else if(snatch.isDisplayed() && movieId.equals("7")){
			snatch.click();
			
		}
		return new MoviePage(driver);
	}
	
	public RegisterPage navigateToRegisterPage() {
		registerNavigationLink.click();
		return new RegisterPage(driver);
	}
}
