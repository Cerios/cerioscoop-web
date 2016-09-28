package nl.cerios.cerioscoop.selenium;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import nl.cerios.cerioscoop.selenium.pages.IndexPage;
import nl.cerios.testutil.SeleniumTest;

public class TodayShowingTest extends SeleniumTest {
	
	private IndexPage homePage;
		
	@Before
	public void navigateToHomePage(){
		homePage = new IndexPage(getWebDriver());
	}
	
	@Test
	public void testNavigateToHomePage(){
		Assert.assertNotNull(homePage);
		Assert.assertEquals("Today Showing", getWebDriver().getTitle());
	}
	
	@Test
	public void checkAmountOfDisplayedMoviesVersusTodaysMoviesInDatabase(){
		List<WebElement> displayedMovies = new ArrayList<WebElement>();
		displayedMovies.addAll(getWebDriver().findElements(By.xpath("//input[contains(@id, 'movietitlebymovieid')]")));
		
		Assert.assertEquals(6, displayedMovies.size());
		Assert.assertEquals(true, displayedMovies.contains(getWebDriver().findElement(By.id("movietitlebymovieid1"))));
		Assert.assertEquals(true, displayedMovies.contains(getWebDriver().findElement(By.id("movietitlebymovieid2"))));
		Assert.assertEquals(true, displayedMovies.contains(getWebDriver().findElement(By.id("movietitlebymovieid4"))));
		Assert.assertEquals(true, displayedMovies.contains(getWebDriver().findElement(By.id("movietitlebymovieid5"))));
		Assert.assertEquals(true, displayedMovies.contains(getWebDriver().findElement(By.id("movietitlebymovieid6"))));
		Assert.assertEquals(true, displayedMovies.contains(getWebDriver().findElement(By.id("movietitlebymovieid7"))));
	}
	
	@Test
	public void checkAmountOfDisplayedShowsVersusTodaysMoviesInDatabase(){
		List<WebElement> displayedShows = new ArrayList<WebElement>();
		displayedShows.addAll(getWebDriver().findElements(By.xpath("//a[contains(@id, 'showtimebyshowid')]")));
		
		Assert.assertEquals(7, displayedShows.size());
		Assert.assertEquals(true, displayedShows.contains(getWebDriver().findElement(By.id("showtimebyshowid1movieid1"))));
		Assert.assertEquals(true, displayedShows.contains(getWebDriver().findElement(By.id("showtimebyshowid2movieid2"))));
		Assert.assertEquals(true, displayedShows.contains(getWebDriver().findElement(By.id("showtimebyshowid3movieid4"))));
		Assert.assertEquals(true, displayedShows.contains(getWebDriver().findElement(By.id("showtimebyshowid4movieid4"))));
		Assert.assertEquals(true, displayedShows.contains(getWebDriver().findElement(By.id("showtimebyshowid5movieid5"))));
		Assert.assertEquals(true, displayedShows.contains(getWebDriver().findElement(By.id("showtimebyshowid6movieid6"))));
		Assert.assertEquals(true, displayedShows.contains(getWebDriver().findElement(By.id("showtimebyshowid7movieid7"))));
	}
	
	@Test
	public void checkDisplayedShowTimesVersusShowTimesInDatabaseOfTheLegendOfTarzan(){
		List<WebElement> displayedShowTimes = new ArrayList<WebElement>();
		displayedShowTimes.addAll(getWebDriver().findElements(By.xpath("//a[contains(@id, 'showtimebyshowid') and contains(@id, 'movieid1')]")));
		
		Assert.assertEquals(1, displayedShowTimes.size());
		Assert.assertEquals(true, displayedShowTimes.contains(getWebDriver().findElement(By.id("showtimebyshowid1movieid1"))));
	}
	
	@Test
	public void checkDisplayedShowTimesVersusShowTimesInDatabaseOfTarzanTheApeMan(){
		List<WebElement> displayedShowTimes = new ArrayList<WebElement>();
		displayedShowTimes.addAll(getWebDriver().findElements(By.xpath("//a[contains(@id, 'showtimebyshowid') and contains(@id, 'movieid2')]")));
		
		Assert.assertEquals(1, displayedShowTimes.size());
		Assert.assertEquals(true, displayedShowTimes.contains(getWebDriver().findElement(By.id("showtimebyshowid2movieid2"))));
	}
	
	@Test
	public void checkDisplayedShowTimesVersusShowTimesInDatabaseOfWeddingCrashers(){
		List<WebElement> displayedShowTimes = new ArrayList<WebElement>();
		displayedShowTimes.addAll(getWebDriver().findElements(By.xpath("//a[contains(@id, 'showtimebyshowid') and contains(@id, 'movieid4')]")));
		
		Assert.assertEquals(2, displayedShowTimes.size());
		Assert.assertEquals(true, displayedShowTimes.contains(getWebDriver().findElement(By.id("showtimebyshowid3movieid4"))));
		Assert.assertEquals(true, displayedShowTimes.contains(getWebDriver().findElement(By.id("showtimebyshowid4movieid4"))));
	}
	
	@Test
	public void checkDisplayedShowTimesVersusShowTimesInDatabaseOfBloodDiamond(){
		List<WebElement> displayedShowTimes = new ArrayList<WebElement>();
		displayedShowTimes.addAll(getWebDriver().findElements(By.xpath("//a[contains(@id, 'showtimebyshowid') and contains(@id, 'movieid5')]")));
		
		Assert.assertEquals(1, displayedShowTimes.size());
		Assert.assertEquals(true, displayedShowTimes.contains(getWebDriver().findElement(By.id("showtimebyshowid5movieid5"))));
	}
	
	@Test
	public void checkDisplayedShowTimesVersusShowTimesInDatabaseOfTheLionKing(){
		List<WebElement> displayedShowTimes = new ArrayList<WebElement>();
		displayedShowTimes.addAll(getWebDriver().findElements(By.xpath("//a[contains(@id, 'showtimebyshowid') and contains(@id, 'movieid6')]")));
		
		Assert.assertEquals(1, displayedShowTimes.size());
		Assert.assertEquals(true, displayedShowTimes.contains(getWebDriver().findElement(By.id("showtimebyshowid6movieid6"))));
	}
	
	@Test
	public void checkDisplayedShowTimesVersusShowTimesInDatabaseOfSnatch(){
		List<WebElement> displayedShowTimes = new ArrayList<WebElement>();
		displayedShowTimes.addAll(getWebDriver().findElements(By.xpath("//a[contains(@id, 'showtimebyshowid') and contains(@id, 'movieid7')]")));
		
		Assert.assertEquals(1, displayedShowTimes.size());
		Assert.assertEquals(true, displayedShowTimes.contains(getWebDriver().findElement(By.id("showtimebyshowid7movieid7"))));
	}
}
