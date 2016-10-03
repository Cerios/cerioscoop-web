package nl.cerios.cerioscoop.selenium;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.domain.Show;
import nl.cerios.cerioscoop.selenium.pages.IndexPage;
import nl.cerios.cerioscoop.service.CustomerDaoImpl;
import nl.cerios.cerioscoop.service.GeneralService;
import nl.cerios.cerioscoop.service.GenericDaoImpl;
import nl.cerios.cerioscoop.service.MovieNotFoundException;
import nl.cerios.cerioscoop.valueobjects.ShowsPresentationVO;
import nl.cerios.testutil.SeleniumTest;

public class TodayShowingTest extends SeleniumTest {
	
	private IndexPage homePage;
	static List<ShowsPresentationVO> todaysShowsTable;

	@InjectMocks
	private GeneralService generalService;
	
	@InjectMocks
	private GenericDaoImpl genericDao;
	
	@InjectMocks
	private CustomerDaoImpl customerDao;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		
		final List<Show> shows = genericDao.getShows();
		final List<Movie> movies = genericDao.getMovies();
		
		try {
			todaysShowsTable = generalService.generateShowTable(shows, movies);
		} catch (MovieNotFoundException e) {
			e.printStackTrace();//TODO: Technische melding van de db-error dat de movie niet gevonden kan worden
		}
	}
	
	@Before
	public void navigateToHomePage(){
		homePage = new IndexPage(getWebDriver());
	}
	
	@Test
	public void testNavigateToHomePage(){
		Assert.assertNotNull(homePage);
		Assert.assertEquals("Today Showing", getWebDriver().getTitle());
	}
	
	/**
	 * todaysShowsTable.get(3).getMovie().getTitle() = get(3) is de 4de movierij die weergegeven wordt
	 */
	@Test
	public void testDisplayedMoviesTitlesVersusTodaysMoviesInDatabase(){
		Assert.assertEquals(todaysShowsTable.get(4).getMovie().getTitle(), getWebDriver().findElement(By.id("movietitlebymovieid6")).getAttribute("value").toString());
		Assert.assertEquals(todaysShowsTable.get(0).getMovie().getTitle(), getWebDriver().findElement(By.id("movietitlebymovieid1")).getAttribute("value").toString());
		Assert.assertEquals(todaysShowsTable.get(5).getMovie().getTitle(), getWebDriver().findElement(By.id("movietitlebymovieid7")).getAttribute("value").toString());
		Assert.assertEquals(todaysShowsTable.get(2).getMovie().getTitle(), getWebDriver().findElement(By.id("movietitlebymovieid4")).getAttribute("value").toString());
		Assert.assertEquals(todaysShowsTable.get(1).getMovie().getTitle(), getWebDriver().findElement(By.id("movietitlebymovieid2")).getAttribute("value").toString());
		Assert.assertEquals(todaysShowsTable.get(3).getMovie().getTitle(), getWebDriver().findElement(By.id("movietitlebymovieid5")).getAttribute("value").toString());
	}
	
	/**
	 * todaysShowsTable.get(0).shows.get(0).getShow() = de eerste movierij met de eerste show van die movierij
	 */
	@Test
	public void checkDisplayedShowTimesVersusShowTimesInDatabaseOfTheLegendOfTarzan(){
		Assert.assertEquals(todaysShowsTable.get(0).shows.get(0).getShow().getShowTime().toString(), 
							getWebDriver().findElement(By.id("showtimebyshowid1movieid1")).getText().toString());
	}
	
	/**
	 * todaysShowsTable.get(1).shows.get(0).getShow() = de tweede movierij met de eerste show van die movierij
	 */
	@Test
	public void checkDisplayedShowTimesVersusShowTimesInDatabaseOfTarzanTheApeMan(){
		Assert.assertEquals(todaysShowsTable.get(1).shows.get(0).getShow().getShowTime().toString(), 
							getWebDriver().findElement(By.id("showtimebyshowid2movieid2")).getText().toString());
	}
	
	/**
	 * todaysShowsTable.get(2).shows.get(0).getShow() = de derde movierij met de eerste + tweede show van die movierij
	 */
	@Test
	public void checkDisplayedShowTimesVersusShowTimesInDatabaseOfWeddingCrashers(){
		Assert.assertEquals(todaysShowsTable.get(2).shows.get(0).getShow().getShowTime().toString(), 
							getWebDriver().findElement(By.id("showtimebyshowid3movieid4")).getText().toString());
		Assert.assertEquals(todaysShowsTable.get(2).shows.get(1).getShow().getShowTime().toString(), 
							getWebDriver().findElement(By.id("showtimebyshowid4movieid4")).getText().toString());
	}
	
	/**
	 * todaysShowsTable.get(3).shows.get(0).getShow() = de vierde movierij met de eerste show van die movierij
	 */
	@Test
	public void checkDisplayedShowTimesVersusShowTimesInDatabaseOfBloodDiamond(){
		Assert.assertEquals(todaysShowsTable.get(3).shows.get(0).getShow().getShowTime().toString(), 
							getWebDriver().findElement(By.id("showtimebyshowid5movieid5")).getText().toString());
	}
	
	/**
	 * todaysShowsTable.get(4).shows.get(0).getShow() = de vijfde movierij met de eerste show van die movierij
	 */
	@Test
	public void checkDisplayedShowTimesVersusShowTimesInDatabaseOfTheLionKing(){
		Assert.assertEquals(todaysShowsTable.get(4).shows.get(0).getShow().getShowTime().toString(), 
							getWebDriver().findElement(By.id("showtimebyshowid6movieid6")).getText().toString());
	}
	
	/**
	 * todaysShowsTable.get(5).shows.get(0).getShow() = de zesde movierij met de eerste show van die movierij
	 */
	@Test
	public void checkDisplayedShowTimesVersusShowTimesInDatabaseOfSnatch(){
		Assert.assertEquals(todaysShowsTable.get(5).shows.get(0).getShow().getShowTime().toString(), 
							getWebDriver().findElement(By.id("showtimebyshowid7movieid7")).getText().toString());
	}
	
	@Test
	public void checkAmountOfDisplayedMoviesVersusTodaysMoviesInDatabase(){
		List<WebElement> displayedMovies = new ArrayList<WebElement>();
		displayedMovies.addAll(getWebDriver().findElements(By.xpath("//input[contains(@id, 'movietitlebymovieid')]")));
		
		Assert.assertEquals(6, displayedMovies.size());
	}
	
	@Test
	public void checkAmountOfDisplayedShowsVersusTodaysMoviesInDatabase(){
		List<WebElement> displayedShows = new ArrayList<WebElement>();
		displayedShows.addAll(getWebDriver().findElements(By.xpath("//a[contains(@id, 'showtimebyshowid')]")));
		
		Assert.assertEquals(7, displayedShows.size());
	}
}
