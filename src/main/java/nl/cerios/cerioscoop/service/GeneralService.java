package nl.cerios.cerioscoop.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import nl.cerios.cerioscoop.ValueObjects.ShowPresentationVO;
import nl.cerios.cerioscoop.ValueObjects.ShowsPresentationVO;
import nl.cerios.cerioscoop.domain.Customer;
import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.domain.MovieBuilder;
import nl.cerios.cerioscoop.domain.Show;
import nl.cerios.cerioscoop.domain.User;
import nl.cerios.cerioscoop.util.DateUtils;

@Stateless										//Stateless is de status van de gevulde opjecten. Best Practice is stateless.
public class GeneralService {

	@Resource(name = "jdbc/cerioscoop")			//Content Dependency Injection techniek
	private DataSource dataSource;
	private DateUtils dateUtils = new DateUtils();
	
	public List<Movie> getMovies(){
		final List<Movie> movies = new ArrayList<>();
		try (final Connection connection = dataSource.getConnection()) {			//AutoCloseable
			final Statement statement = connection.createStatement();
			final ResultSet resultSet = statement.executeQuery("SELECT movie_id, title, movie_description FROM movie");
			while (resultSet.next()) {
				final Movie movie = new MovieBuilder()
						.withMovieId(resultSet.getBigDecimal("movie_id").toBigInteger())
						.withMovieTitle(resultSet.getString("title"))
						.withMovieDescription(resultSet.getString("movie_description"))
						.build();
				movies.add(movie);
			}
			return movies;
	    }catch (final SQLException e) {
	    	throw new ServiceException("Something went terribly wrong while retrieving the movie.", e);
	    }
	}
	
	public List<Show> getShows(){
		final List<Show> shows = new ArrayList<>();
		try (final Connection connection = dataSource.getConnection()){
			final Statement statement = connection.createStatement();
			final ResultSet resultSet = statement.executeQuery("SELECT show_id, movie_id, room_id, show_date, show_time, available_places, show_price FROM show_table"); { 
			while (resultSet.next()) {
				final int showId = resultSet.getInt("show_id");
				final int movieId = resultSet.getInt("movie_id");
				final int roomId = resultSet.getInt("room_id");
				final Date showDate = resultSet.getDate("show_date");
				final Time showTime = resultSet.getTime("show_time");
				final int availablePlaces = resultSet.getInt("available_places");
				final float showPrice = resultSet.getInt("show_price");
				
	        	shows.add(new Show(showId, movieId, roomId, showDate, showTime, availablePlaces, showPrice));
	        	}
	        return shows;
	      }
	    }catch (final SQLException e) {
	    	throw new ServiceException("Something went terribly wrong while retrieving the first date.", e);
	    }
	}
	
	public List<Customer> getCustomers(){
		final List<Customer> customers = new ArrayList<>();
		try (final Connection connection = dataSource.getConnection()){
			final Statement statement = connection.createStatement();
			final ResultSet resultSet = statement.executeQuery("SELECT customer_id, first_name, last_name, username, password, email FROM customer"); { 

			while (resultSet.next()) {
				final int customerId = resultSet.getInt("customer_id");
				final String firstName = resultSet.getString("first_name");
				final String lastName = resultSet.getString("last_name");
				final String username = resultSet.getString("username");
				final String password = resultSet.getString("password");
				final String email = resultSet.getString("email");

				customers.add(new Customer(customerId, firstName, lastName, username, password, email));
	        	}
	        return customers;
	      }
	    }catch (final SQLException e) {
	    	throw new ServiceException("Something went terribly wrong while retrieving the customers.", e);
	    }
	}
	
	/**
	 * Returns a first showing record.
	 * 
	 * @return firstShowing
	 */
	public Show getFirstShowforToday(final List<Show> listOfShows){
		Show firstShow = null;	
		for (final Show show : listOfShows) {
			if(dateUtils.toDateTime(show.getShowDate(), show.getShowTime()).after(dateUtils.getCurrentSqlTime())){	
				if(firstShow == null){			//hier wordt voor 1x eerstVolgendeFilm gevuld					
					firstShow = show;
				}
				else if(show.getShowTime().before(firstShow.getShowTime())){
					firstShow = show;		
				}
			}
		}
		return firstShow;
	}
		
	public Movie getMovieByMovieId(final int movieId, final List<Movie> listOfMovies) throws MovieNotFoundException {
		final List<Movie> movies = listOfMovies;
		Movie movieByMovieId = null;
		
		for (final Movie movieItem : movies){
			if (movieItem.getMovieId().intValue() == movieId) {
				movieByMovieId = movieItem;
			}
		}
		return movieByMovieId;
	}
	
	public void registerCustomer(final Customer customer){
		try (final Connection connection = dataSource.getConnection();
				final PreparedStatement preparedStatement = connection.prepareStatement(
						"INSERT INTO customer (first_name, last_name, username, password, email) VALUES (?,?,?,?,?)")) {
				
	        	preparedStatement.setString(1, customer.getFirstName());
	        	preparedStatement.setString(2, customer.getLastName());
	        	preparedStatement.setString(3, customer.getUsername());
	        	preparedStatement.setString(4, customer.getPassword());
	        	preparedStatement.setString(5, customer.getEmail());
	        	preparedStatement.executeUpdate();
	        	
	        	System.out.println("Data inserted.");
		    }catch (final SQLException e) {
		    	throw new ServiceException("Something went wrong while inserting the customer items.", e);
		    }
	}
	
	public User authenticateCustomer(User customer, List<Customer> listOfCustomers){
		final List<Customer> dbCustomers = listOfCustomers;		
		final String usernameCustomer = customer.getUsername();
		final String passwordCustomer = customer.getPassword();
		User authenticatedCustomer = null;
		
		for (final Customer customerItem : dbCustomers){
			if(customerItem.getUsername().equals(usernameCustomer) && customerItem.getPassword().equals(passwordCustomer)){
				authenticatedCustomer = customerItem;
				}
		}
		return authenticatedCustomer;
	}
	
	public Boolean authenticateUser(User authenticatedUser){
		if(authenticatedUser == null){
			return false;
		}
		return true;
	}
	
	public List<ShowsPresentationVO> generateShowTable(final List<Show> shows, final List<Movie> movies) throws MovieNotFoundException {
		List<ShowsPresentationVO> todaysShowsTable = new ArrayList<ShowsPresentationVO>();

		// voeg alle shows toe aan de tabel
		for (Show todaysShow : shows) {
			ShowsPresentationVO existingShowsPresentationVORow = null; // checkt of de movie van de huidige tabel al is opgenomen
			for (ShowsPresentationVO showsRowIter : todaysShowsTable) {
				if (todaysShow.getMovieId() == showsRowIter.getMovie().getMovieId().intValue()) {// hier bestaat de movie al in de index
					ShowPresentationVO newShowPresentationVO = new ShowPresentationVO();
					newShowPresentationVO.setShow(todaysShow);
					newShowPresentationVO.setSoldOut(checkIfThereAreNoAvailablePlaces(todaysShow.getAvailablePlaces()));			
					showsRowIter.shows.add(newShowPresentationVO);
					existingShowsPresentationVORow = showsRowIter;
				}
			}
			if (existingShowsPresentationVORow == null) {//Nieuwe MovieRow worst gemaakt
				ShowPresentationVO newShowPresentationVO = new ShowPresentationVO();
				newShowPresentationVO.setShow(todaysShow);
				newShowPresentationVO.setSoldOut(checkIfThereAreNoAvailablePlaces(todaysShow.getAvailablePlaces()));
				
				ShowsPresentationVO newShowsPresentationRowVO = new ShowsPresentationVO();			
				List<ShowPresentationVO> showPresentationVOList = new ArrayList<ShowPresentationVO>();
				showPresentationVOList.add(newShowPresentationVO);
				newShowsPresentationRowVO.setMovie(getMovieByMovieId(todaysShow.getMovieId(), movies));
				newShowsPresentationRowVO.setShowsPresentationVO(showPresentationVOList);
				todaysShowsTable.add(newShowsPresentationRowVO);
			}
		}
		return todaysShowsTable;
	}
	
	public String generateRandomUsername(){
		char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		return sb.toString();
	}
	
	public Boolean checkIfThereAreNoAvailablePlaces(int availablePlaces){
		if(availablePlaces == 0){
			return true;
		}else{
			return false;
		}
	}
}


