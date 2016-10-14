package nl.cerios.cerioscoop.selenium;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.domain.MovieBuilder;
import nl.cerios.cerioscoop.domain.Show;
import nl.cerios.cerioscoop.selenium.pages.IndexPage;
import nl.cerios.cerioscoop.service.GenericDaoImpl;
import nl.cerios.cerioscoop.valueobjects.ShowPresentationVO;
import nl.cerios.cerioscoop.valueobjects.ShowsPresentationVO;
import nl.cerios.testutil.SeleniumTest;

public class TodayShowingTest extends SeleniumTest {
	
	private IndexPage homePage;
	private static List<ShowsPresentationVO> todaysShowsTable = new ArrayList<>();
	private static List<Movie> todaysMovieTitles = new ArrayList<>();
	private static final String currentDate = LocalDate.now().toString();
	private static final Logger LOG = LoggerFactory.getLogger(GenericDaoImpl.class);

	@BeforeClass
	public static void initDataSets() throws SQLException {	
		try (final Connection connection = getDataSource().getConnection();
			final PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT M.title, S.show_date, S.show_time"
					+ " FROM show_table S"
					+ " INNER JOIN movie M on M.movie_id = S.movie_id"
					+ " WHERE show_date = ?"
					+ " ORDER BY S.show_time")) {
			preparedStatement.setString(1, currentDate);
    		ResultSet resultSet = preparedStatement.executeQuery();{	
				while (resultSet.next()) {
					List<ShowPresentationVO> showTimes = new ArrayList<>();
					ShowPresentationVO showPresentationVO = new ShowPresentationVO();
					ShowsPresentationVO showsPresentationVO = new ShowsPresentationVO();
					
					Movie movie = new MovieBuilder().withMovieTitle(resultSet.getString("title")).build();
					Show show = new Show();	
					show.setShowDate(resultSet.getDate("show_date"));
					show.setShowTime(resultSet.getTime("show_time"));
					show.setMovie(movie);
					
					showPresentationVO.setShow(show);
					showTimes.add(showPresentationVO);
					
					showsPresentationVO.setMovie(movie);
					showsPresentationVO.setShowsPresentationVO(showTimes);
					
					todaysShowsTable.add(showsPresentationVO);
				}
    		}
		}
		try (final Connection connection = getDataSource().getConnection();
				final PreparedStatement preparedStatement = connection.prepareStatement(
						"SELECT M.title"
						+ " FROM show_table S"
						+ " INNER JOIN movie M on M.movie_id = S.movie_id"
						+ " WHERE show_date = ?"
						+ " GROUP BY M.title"
						+ " ORDER BY S.show_time")) {
				preparedStatement.setString(1, currentDate);
	    		ResultSet resultSet = preparedStatement.executeQuery();{	
					while (resultSet.next()) {				
						Movie movie = new MovieBuilder().withMovieTitle(resultSet.getString("title")).build();
						todaysMovieTitles.add(movie);
					}
	    		}
			}
		
	}
	
	@Before
	public void navigateToHomePage(){
		homePage = new IndexPage(getWebDriver());
	}
	
	//Pageview
	@Test
	public void testNavigateToHomePage(){
		Assert.assertNotNull(homePage);
		Assert.assertEquals("Today Showing", getWebDriver().getTitle());
	}
	
	@Test
	public void testDisplayedNavigationBarOnTodayShowingScreen(){
		Assert.assertTrue(getWebDriver().findElement(By.className("navbar")).isDisplayed());
		LOG.info("Navbar is displayed!");
	}
	
	@Test
	public void testDisplayedHeadersOnTodayShowingScreen(){
		Assert.assertEquals("Today Showing", getWebDriver().findElement(By.xpath("/html/body/h1")).getText().toString());
		Assert.assertEquals("Movietitle", getWebDriver().findElement(By.xpath("/html/body/table/thead/tr/th[1]")).getText().toString());
		Assert.assertEquals("Movie times", getWebDriver().findElement(By.xpath("/html/body/table/thead/tr/th[2]")).getText().toString());
		LOG.info("Today Showing, Movietitle and Movie times are displayed!");
	}
	
	@Test
	public void testDisplayedMoviesTitlesOnTodayShowingScreen(){
		LOG.info("Is the list empty? " + todaysMovieTitles.size());
		if(todaysMovieTitles.size() != 0)
		for(Movie movie : todaysMovieTitles){
			// staat movie.getTitle() op het scherm?
			if(getWebDriver().getTitle().contains("Today Showing")){
				Assert.assertTrue(getWebDriver().getPageSource().contains(movie.getTitle()));
				LOG.info(movie.getTitle() + " is displayed on th screen.");
			}
		}
	}
	
	@Test
	public void testDisplayedShowTimesOnTodayShowingScreen(){
		LOG.info("Is the list empty? " + todaysShowsTable.size());
		if(todaysShowsTable.size() != 0)
		for(ShowsPresentationVO showsPresentationVO : todaysShowsTable){
			for(ShowPresentationVO showPresentationVO : showsPresentationVO.getShowsPresentationVO()){
				// staat showPresentationVO.getShow().getShowTime().toString() op het scherm?
				if(getWebDriver().getTitle().contains("Today Showing")){
					Assert.assertTrue(getWebDriver().getPageSource().contains(showPresentationVO.getShow().getShowTime().toString()));
					LOG.info(showPresentationVO.getShow().getShowTime().toString() + " is displayed on th screen.");
				}
			}
		}
	}
	
	//Functional
	@Test
	public void checkAmountOfDisplayedMoviesVersusTodaysMoviesInDatabase(){
		List<WebElement> displayedMovies = new ArrayList<WebElement>();
		displayedMovies.addAll(getWebDriver().findElements(By.xpath("//input[contains(@id, 'movietitlebymovieid')]")));
		
		Assert.assertEquals(todaysMovieTitles.size(), displayedMovies.size());
	}
	
	@Test
	public void checkAmountOfDisplayedShowsVersusTodaysMoviesInDatabase(){
		List<WebElement> displayedShows = new ArrayList<WebElement>();
		displayedShows.addAll(getWebDriver().findElements(By.xpath("//input[contains(@id, 'showtimebyshowid')]")));
		
		Assert.assertEquals(todaysShowsTable.size(), displayedShows.size());
	}
}
