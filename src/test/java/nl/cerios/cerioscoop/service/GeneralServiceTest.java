package nl.cerios.cerioscoop.service;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import nl.cerios.cerioscoop.dao.CustomerDaoImpl;
import nl.cerios.cerioscoop.dao.ShowDaoImpl;
import nl.cerios.cerioscoop.domain.Customer;
import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.domain.MovieBuilder;
import nl.cerios.cerioscoop.domain.Show;
import nl.cerios.cerioscoop.util.DateUtils;
import nl.cerios.cerioscoop.valueobjects.ShowsPresentationVO;
import nl.cerios.testutil.DerbyDatabaseTest;

public class GeneralServiceTest extends DerbyDatabaseTest {

	@InjectMocks
	private ShowService showService;
	
	@InjectMocks
	private ShowDaoImpl showDao;
	
	@InjectMocks
	private CustomerDaoImpl customerDao;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetMovies() {
		final List<Movie> movies = showDao.getMovies();
		Assert.assertNotNull(movies);
		Assert.assertEquals(7, movies.size());
	}
	
	@Test
	public void testGetShows() {
		final List<Show> shows = showDao.getShows();

		Assert.assertNotNull(shows);
		Assert.assertEquals(7, shows.size());
	}
		
	@Test
	public void testGetCustomers() {
		final List<Customer> customers = customerDao.getCustomers();

		Assert.assertNotNull(customers);
		Assert.assertEquals(3, customers.size());
	}	
	
	@Test
	public void testGetFirstShowforToday() throws ParseException{
	//Movie for the shows
		final Movie movie = new MovieBuilder().withMovieTitle("LekkereMovie").build();
	//Shows	
		final Show showOne = new Show(0, movie, 
				DateUtils.convertUtilDateToSqlDate(DateUtils.toDate(DateUtils.toDateFormat("07-20-2020"))),
				DateUtils.convertUtilDateToSqlTime(DateUtils.toTime(DateUtils.toTimeFormat("20:00:00"))));
		final Show showTwo = new Show(0, movie, 
				DateUtils.convertUtilDateToSqlDate(DateUtils.toDate(DateUtils.toDateFormat("07-23-2020"))),
				DateUtils.convertUtilDateToSqlTime(DateUtils.toTime(DateUtils.toTimeFormat("20:00:00"))));
		final Show showThree = new Show(0, movie, 
				DateUtils.convertUtilDateToSqlDate(DateUtils.toDate(DateUtils.toDateFormat("09-03-2020"))),
				DateUtils.convertUtilDateToSqlTime(DateUtils.toTime(DateUtils.toTimeFormat("20:00:00"))));	
		
	//Putting all movies in a list
		final List<Show> listOfShows = new ArrayList<>();
		listOfShows.add(0, showOne);
		listOfShows.add(1, showTwo);
		listOfShows.add(2, showThree);
		
	//First show after the current date control 
		Assert.assertEquals(showOne.getShowDate() ,showService.getFirstShowforToday(listOfShows).getShowDate());
		Assert.assertNotEquals(showTwo.getShowDate() ,showService.getFirstShowforToday(listOfShows).getShowDate());
		Assert.assertNotEquals(showThree.getShowDate() ,showService.getFirstShowforToday(listOfShows).getShowDate());
	}
	@Test
	public void testGetMovieByMovieId() throws MovieNotFoundException{
	//Movies	
		final Movie movieOne = new MovieBuilder()
				.withMovieId(BigInteger.valueOf(1))
				.withMovieTitle("top titel")
				.withMovieDescription("bagger v-film")
				.build();
		final Movie movieTwo = new MovieBuilder()
				.withMovieId(BigInteger.valueOf(2))
				.withMovieTitle("lekkere titel")
				.withMovieDescription("bagger v-film")
				.build();
		final Movie movieThree = new MovieBuilder()
				.withMovieId(BigInteger.valueOf(3))
				.withMovieTitle("keke titel")
				.withMovieDescription("bagger v-film")
				.build();
		
	//Putting all movies in a list
		final List<Movie> listOfMovies = new ArrayList<>();
		listOfMovies.add(0, movieOne);
		listOfMovies.add(1, movieTwo);
		listOfMovies.add(2, movieThree);
		
	//Movie control 
			Assert.assertEquals(movieOne ,showService.getMovieByMovieId(movieOne.getMovieId().intValue(), listOfMovies));
			Assert.assertEquals(movieTwo ,showService.getMovieByMovieId(movieTwo.getMovieId().intValue(), listOfMovies));
			Assert.assertEquals(movieThree ,showService.getMovieByMovieId(movieThree.getMovieId().intValue(), listOfMovies));
	}
	
	@Test
	public void testRegisterCustomer() throws ParseException {
		final int idOfCustomerToBeRegistered = 4;
		final Customer customerOne = new Customer(idOfCustomerToBeRegistered, "Michael", "Boogerd", "MB", "MB123", "michael@boogerd.com");
		
		final Customer customerBefore = getCustomer(idOfCustomerToBeRegistered);
		Assert.assertNull(customerBefore);
		
		customerDao.registerCustomer(customerOne);

		final Customer customerAfter = getCustomer(idOfCustomerToBeRegistered);
		Assert.assertNotNull(customerAfter);
		Assert.assertEquals(customerOne.getFirstName(), customerAfter.getFirstName());
		Assert.assertEquals(customerOne.getLastName(), customerAfter.getLastName());
		Assert.assertEquals(customerOne.getUsername(), customerAfter.getUsername());
		Assert.assertEquals(customerOne.getPassword(), customerAfter.getPassword());
		Assert.assertEquals(customerOne.getEmail(), customerAfter.getEmail());
	}
	
	@Test
	public void testAuthenticateCustomer() throws ParseException {
	//Customers
		final Customer customerOne = new Customer(0, "Bauke", "Mollema", "BM", "BM123", "bauke@mollema.com");
		final Customer customerTwo = new Customer(1, "Tom", "Dumoulin", "TD", "TD123", "tom@dumoulin.com");
		final Customer customerThree = new Customer(2, "Stef", "Clement", "SC", "SC123", "stef@clement.com");
	
	//The no-customer test-user
		final Customer noCustomer = new Customer(3, "Chris", "Froome", "CF", "CF123", "chris@froome.com");
		
	//Putting all customers in a list
		final List<Customer> dbCustomers = new ArrayList<>();
		dbCustomers.add(0, customerOne);
		dbCustomers.add(1, customerTwo);
		dbCustomers.add(2, customerThree);
		
	//Authentication control 
		Assert.assertEquals(customerOne ,showService.authenticateCustomer(customerOne, dbCustomers));
		Assert.assertEquals(customerTwo ,showService.authenticateCustomer(customerTwo, dbCustomers));
		Assert.assertEquals(customerThree ,showService.authenticateCustomer(customerThree, dbCustomers));
		Assert.assertNotEquals(noCustomer ,showService.authenticateCustomer(noCustomer, dbCustomers));
	}
	
	@Test
	public void testAuthenticateUser() {
		final Customer testUser = null;
		Assert.assertEquals(false, showService.authenticateUser(testUser));
		final Customer testCustomer = new Customer(1, "Marcel", "Groothuis", "Manollo7G", "secret", "mjg@cerios.nl");
		Assert.assertEquals(true, showService.authenticateUser(testCustomer));
	}
	
	@Test 
	public void testGenerateShowTableWithEmptyShowsAndMovies() throws ParseException, MovieNotFoundException {		
		List<ShowsPresentationVO> emptyTodaysShowsTable = new ArrayList<ShowsPresentationVO>();
		final List<Show> testShows = new ArrayList<>();
		final List<Movie> testMovies = new ArrayList<>();		
	
	//todaysShowsTable vullen met lege shows en movies.
		emptyTodaysShowsTable = showService.generateShowTable(testShows, testMovies);

	//Check eerste unittest
		Assert.assertEquals(emptyTodaysShowsTable, showService.generateShowTable(testShows, testMovies));
		Assert.assertEquals(0, emptyTodaysShowsTable.size());
	}
		
	@Test 
	public void testGenerateShowTableWithDerbyShowsAndMovies() throws ParseException, MovieNotFoundException {		
		List<ShowsPresentationVO> filledTodaysShowsTable = new ArrayList<ShowsPresentationVO>();	
		
		//Tweede unittest: lijsten maken met derby vulling After
			final List<Show> shows = showDao.getShows();
			final List<Movie> movies = showDao.getMovies();	
			
		//de todaysShowsTable vullen met de lege shows en movies.
			filledTodaysShowsTable = showService.generateShowTable(shows, movies);	
			
		//voorstellingen in tweede unittest
			ShowsPresentationVO TheLegendOfTarzan = null;
			ShowsPresentationVO TarzanTheApeMan = null;
			ShowsPresentationVO Tarzan = null;	
			ShowsPresentationVO WeddingCrashers = null;
			ShowsPresentationVO BloodDiamond = null;
			ShowsPresentationVO TheLionKing = null;	
			ShowsPresentationVO Snatch = null;
			
			for (ShowsPresentationVO showsPresentationVO : filledTodaysShowsTable){
				if(1 == showsPresentationVO.getMovie().getMovieId().intValue()){
					TheLegendOfTarzan = showsPresentationVO;
				}else if(2 == showsPresentationVO.getMovie().getMovieId().intValue()){
					TarzanTheApeMan = showsPresentationVO;
				}else if(3 == showsPresentationVO.getMovie().getMovieId().intValue()){
					Tarzan = showsPresentationVO;
				}else if(4 == showsPresentationVO.getMovie().getMovieId().intValue()){
					WeddingCrashers = showsPresentationVO;
				}else if(5 == showsPresentationVO.getMovie().getMovieId().intValue()){
					BloodDiamond = showsPresentationVO;
				}else if(6 == showsPresentationVO.getMovie().getMovieId().intValue()){
					TheLionKing = showsPresentationVO;
				}else if(7 == showsPresentationVO.getMovie().getMovieId().intValue()){
					Snatch = showsPresentationVO;
				}
			}
			Assert.assertEquals(filledTodaysShowsTable.size(), showService.generateShowTable(shows, movies).size());
			Assert.assertEquals(6, filledTodaysShowsTable.size());
			Assert.assertEquals(1, TheLegendOfTarzan.getMovie().getMovieId().intValue());
			Assert.assertEquals("The Legend of Tarzan", TheLegendOfTarzan.getMovie().getTitle());
			Assert.assertEquals(1, TheLegendOfTarzan.getShowsPresentationVO().size());				
			Assert.assertEquals(2, TarzanTheApeMan.getMovie().getMovieId().intValue());
			Assert.assertEquals("Tarzan the Ape Man", TarzanTheApeMan.getMovie().getTitle());
			Assert.assertEquals(1, TarzanTheApeMan.getShowsPresentationVO().size());
			Assert.assertEquals(4, WeddingCrashers.getMovie().getMovieId().intValue());
			Assert.assertEquals("Wedding Crashers", WeddingCrashers.getMovie().getTitle());
			Assert.assertEquals(2, WeddingCrashers.getShowsPresentationVO().size());
			Assert.assertEquals(5, BloodDiamond.getMovie().getMovieId().intValue());
			Assert.assertEquals("Blood Diamond", BloodDiamond.getMovie().getTitle());
			Assert.assertEquals(1, BloodDiamond.getShowsPresentationVO().size());
			Assert.assertEquals(6, TheLionKing.getMovie().getMovieId().intValue());
			Assert.assertEquals("The Lion King", TheLionKing.getMovie().getTitle());
			Assert.assertEquals(1, TheLionKing.getShowsPresentationVO().size());
			Assert.assertEquals(7, Snatch.getMovie().getMovieId().intValue());
			Assert.assertEquals("Snatch", Snatch.getMovie().getTitle());
			Assert.assertEquals(1, Snatch.getShowsPresentationVO().size());
			Assert.assertNull(Tarzan);
		}
	
	
	private Customer getCustomer(final int customerID) {
		return customerDao.getCustomers().stream()
				.filter(c -> c.getCustomerId() == customerID)
				.findAny()
				.orElse(null);
	}
}
