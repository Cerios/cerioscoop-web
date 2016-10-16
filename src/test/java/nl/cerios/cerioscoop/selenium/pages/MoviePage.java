package nl.cerios.cerioscoop.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MoviePage {

	@FindBy(id = "movie-title")
	private WebElement movieTitle;
	
	@FindBy(id = "movie-description")
	private WebElement MovieDescription;
	

	public MoviePage(WebDriver driver) {
		// This call sets the WebElement fields.
		PageFactory.initElements(driver, this);
	}

	public String getMovieTitleFromMoviePage() {
		 return movieTitle.getText();
	}
	
	public String getMovieDescriptionFromMoviePage() {
		 return MovieDescription.getText();
	}
}
