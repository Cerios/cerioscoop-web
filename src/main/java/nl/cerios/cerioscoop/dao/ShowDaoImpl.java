package nl.cerios.cerioscoop.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.domain.MovieBuilder;
import nl.cerios.cerioscoop.domain.Room;
import nl.cerios.cerioscoop.domain.Show;
import nl.cerios.cerioscoop.service.ServiceException;
import nl.cerios.cerioscoop.util.DateUtils;

@Stateless
public class ShowDaoImpl{
	
	private static final Logger LOG = LoggerFactory.getLogger(ShowDaoImpl.class);
	
	@Resource(name = "jdbc/cerioscoop")
	private DataSource dataSource;
	
	
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
	
	
	public Movie getMovieByMovieId(int movieId) {
		Movie movie = null;
		try (final Connection connection = dataSource.getConnection()) {
			final PreparedStatement preparedstatement = connection
					.prepareStatement("SELECT movie_id, title, movie_description FROM movie WHERE movie_id = ?");
			preparedstatement.setInt(1, movieId);
			ResultSet resultSet = preparedstatement.executeQuery();
			{
				while (resultSet.next()) {
					movie = new MovieBuilder().withMovieId(resultSet.getBigDecimal("movie_id").toBigInteger())
							.withMovieTitle(resultSet.getString("title"))
							.withMovieDescription(resultSet.getString("movie_description"))
							.build();
				}
				return movie;
			}
		} catch (final SQLException e) {
			throw new ServiceException("Something went terribly wrong while retrieving the movie.", e);
		}
	}
	
	
	public List<Show> getShows(){
		final List<Show> shows = new ArrayList<>();
		try (final Connection connection = dataSource.getConnection()){
			final Statement statement = connection.createStatement();
			final ResultSet resultSet = statement.executeQuery("SELECT show_id, movie_id, room_id, show_date, show_time, tickets_sold, show_price FROM show_table"); { 
			while (resultSet.next()) {
				final int showId = resultSet.getInt("show_id");
				final Movie movie = new MovieBuilder().withMovieId(resultSet.getBigDecimal("movie_id").toBigInteger()).build();
				Room room = new Room();
				room.setRoomId(resultSet.getInt("room_id"));
				final Date showDate = resultSet.getDate("show_date");
				final Time showTime = resultSet.getTime("show_time");
				final int numberOfTicketsSold = resultSet.getInt("tickets_sold");
				final float showPrice = resultSet.getInt("show_price");
				
	        	shows.add(new Show(showId, movie, room, showDate, showTime, numberOfTicketsSold, showPrice));
	        	}
	        return shows;
	      }
	    }catch (final SQLException e) {
	    	throw new ServiceException("Something went terribly wrong while retrieving the first date.", e);
	    }
	}
	
	
	public List<Show> getShowsForToday(){
		final List<Show> shows = new ArrayList<>();
		try (final Connection connection = dataSource.getConnection()){
			final Statement statement = connection.createStatement();
			final ResultSet resultSet = statement.executeQuery("SELECT show_id, movie_id, room_id, show_date, show_time, tickets_sold, show_price FROM show_table WHERE show_date = CURDATE()"); { 

			while (resultSet.next()) {
				final int showId = resultSet.getInt("show_id");
				final Movie movie = new MovieBuilder().withMovieId(resultSet.getBigDecimal("movie_id").toBigInteger()).build();
				final Room room = new Room(); 
				room.setRoomId(resultSet.getInt("room_id"));
				room.setCapacity(getRoomCapacityByRoomId(room.getRoomId())); //TODO refactor SELECT query
				final Date showDate = resultSet.getDate("show_date");
				final Time showTime = resultSet.getTime("show_time");
				final int numberOfTicketsSold = resultSet.getInt("tickets_sold");
				final float showPrice = resultSet.getInt("show_price");
				shows.add(new Show(showId, movie, room, showDate, showTime, numberOfTicketsSold, showPrice));
        	}
        return shows;
	      }
	    }catch (final SQLException e) {
	    	throw new ServiceException("Something went terribly wrong while retrieving the ShowingList.", e);
	    }
	}    
    
	
    /**
     * @param showid
     * @return
     * 
     * TODO Use a constructor to set show values, so it can be immutable later
     */
    public Show getShowById(int showid){
    	Show show = new Show();
       	String SQL = "SELECT S.show_id, M.title, R.room_name, S.tickets_sold, S.show_price FROM show_table S INNER JOIN movie M on M.movie_id = S.movie_id INNER JOIN room R on R.room_id = S.room_id WHERE S.show_id = ?";
    	try (final Connection connection = dataSource.getConnection()) {
			final PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, showid);
    		ResultSet resultSet = preparedStatement.executeQuery();
    		{
				while (resultSet.next()) {
				Room room = new Room();
				room.setRoomName(resultSet.getString("R.room_name"));

				Movie movie = new MovieBuilder().withMovieTitle(resultSet.getString("M.title")).build();
				show.setShowId(resultSet.getInt("S.show_id"));
				show.setMovie(movie);
				show.setRoom(room);
				show.setTicketsSold(resultSet.getInt("S.tickets_sold"));
				show.setShowPrice(resultSet.getInt("S.show_price"));
				LOG.debug("SQL Showprice: " + show.getShowPrice());
				}
				LOG.debug("Transaction(s) retrieved.");
				return show;
			}
      
        }catch (final SQLException e) {
            throw new ServiceException("Something went wrong while retrieving the transactions.", e);
        }
    }
    
	/**
	 * addNewShows adds new show records to the database
	 * 
	 * De voorbeeldJsonFile.json zit in src/main/resources, zorg dat er niet meer record aanwezig zijn
	 * dan in mysql-testdata.sql!! Dit is te testen door te klikken op UPDATE SHOWS. Eerst moet je inloggen!
	 * 
	 * http://stackoverflow.com/questions/10926353/how-to-read-json-file-into-
	 * java-with-simple-json-library
	 * http://stackoverflow.com/questions/6514876/most-efficient-conversion-of-
	 * resultset-to-json
	 */
	public void addNewShows() {
		JSONParser parser = new JSONParser();
		JSONArray newShows = null;
		
		try {
			newShows = (JSONArray) parser.parse(new FileReader("c:\\voorbeeldJsonFile.json"));
		} catch (FileNotFoundException e3) {
			e3.printStackTrace();
		} catch (IOException e3) {
			e3.printStackTrace();
		} catch (ParseException e3) {
			e3.printStackTrace();
		}

		try (final Connection connection = dataSource.getConnection()) {
			//First insert the movie
			try (final PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO movie (movie_id, title, movie_description) VALUES (?,?,?)")) {
				for (Object Show : newShows) {
					JSONObject newShow = (JSONObject) Show;
					JSONArray movie = (JSONArray) newShow.get("movie");
					for (Object obj : movie) {
						JSONObject movieObject = (JSONObject) obj;
						preparedStatement.setBigDecimal(1, new BigDecimal((String) movieObject.get("movie_id")));
						preparedStatement.setString(2, (String) movieObject.get("title"));
						preparedStatement.setString(3, (String) movieObject.get("movie_description"));
						preparedStatement.executeUpdate();
						LOG.debug("Movie inserted.");
					}
				}
			} catch (SQLException e1) {
				throw new ServiceException("Something went wrong while inserting the movie.", e1);
			}
			
			//Second insert the show
			try (final PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO show_table (movie_id, room_id, show_date, show_time, tickets_sold, show_price) VALUES (?,?,?,?,?,?)")) {
				for (Object Show : newShows) {
					// Er moet eigenlijk eerst nog gecheckt worden of de movie al bestaat
					JSONObject newShow = (JSONObject) Show;
					JSONArray movie = (JSONArray) newShow.get("movie");
					BigDecimal movieID = null;
					
					for (Object obj : movie) {
						JSONObject movieObject = (JSONObject) obj;
						movieID = new BigDecimal((String) movieObject.get("movie_id"));
					}
					
					preparedStatement.setBigDecimal(1, movieID);
					preparedStatement.setInt(2, Integer.parseInt((String) newShow.get("room_id")));
					preparedStatement.setDate(3, DateUtils.convertStringToSqlDate((String) newShow.get("show_date")));
					preparedStatement.setTime(4, DateUtils.convertStringToSqlTime((String) newShow.get("show_time")));
					preparedStatement.setInt(5, Integer.parseInt((String) newShow.get("tickets_sold")));
					preparedStatement.setFloat(6, Float.parseFloat((String) newShow.get("show_price")));
					preparedStatement.executeUpdate();
					LOG.debug("Show inserted.");
				}
			} catch (final SQLException e) {
				throw new ServiceException("Something went wrong while inserting the customer items.", e);
			} catch (java.text.ParseException e) {
				throw new ServiceException("Something went wrong while parsing.", e);
			}
		} catch (SQLException e2) {
			throw new ServiceException("Something went wrong while connectiing to the database.", e2);
		}
	}
         
    
    public void updateNumberOfTicketsSold(int numberOfTicketsSold, int showId) {
		try {
			final Connection connection = dataSource.getConnection();
			final PreparedStatement preparedStatement = connection.prepareStatement("UPDATE show_table SET tickets_sold = tickets_sold + ? WHERE show_id = ?");

			preparedStatement.setInt(1, (numberOfTicketsSold));
			preparedStatement.setInt(2, showId);
			preparedStatement.executeUpdate();

			LOG.debug("Tickets_sold is updated.");
		} catch (final SQLException e) {
			throw new ServiceException("Something went wrong while updating the numberOfTicketsSold.", e);
		}
	}
    
    public int getRoomCapacityByRoomId(int roomId){
		int roomCapacity = 0;
		try (final Connection connection = dataSource.getConnection()) {
			final PreparedStatement preparedstatement = connection
					.prepareStatement("SELECT capacity FROM room WHERE room_id = ?");
			preparedstatement.setInt(1, roomId);
			ResultSet resultSet = preparedstatement.executeQuery();
			{
				if(resultSet.next()) {
					roomCapacity = resultSet.getInt("capacity");	
				}
			return roomCapacity;
			}
		} catch (final SQLException e) {
			throw new ServiceException("Something went terribly wrong while retrieving the room.", e);
		}
	}
}
