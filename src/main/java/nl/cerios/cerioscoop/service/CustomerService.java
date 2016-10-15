package nl.cerios.cerioscoop.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import nl.cerios.cerioscoop.domain.Show;

@Stateless
public class CustomerService {

	@Resource(name = "jdbc/cerioscoop")
	private DataSource dataSource;

	public void updateChairsSold(int chairsSold, int showingId) {

		String updateSQL = "UPDATE show_table SET chairs_sold = chairs_sold +? WHERE show_id = ?";

		try {
			final Connection connection = dataSource.getConnection();
			final PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setInt(1, (chairsSold));
			preparedStatement.setInt(2, showingId);
			preparedStatement.executeUpdate();

			System.out.println("Chairs_sold is updated.");
		} catch (final SQLException e) {
			throw new ServiceException("Something went wrong while updating the chairsSoldAmount.", e);
		}
	}

	public Show getShowByShowId(int show_id) {
		Show show = new Show();
		String selectSQL = "SELECT movie_title, show_date, show_time FROM show_presentation WHERE show_id = ?";

		try (final Connection connection = dataSource.getConnection()) {
			final PreparedStatement preparedstatement = connection.prepareStatement(selectSQL);
			preparedstatement.setInt(1, show_id);
			ResultSet resultSet = preparedstatement.executeQuery();{
				while (resultSet.next()) {
				final int showId = show_id;
				final int movieId = resultSet.getInt("movie_id");
				final int roomId = resultSet.getInt("room_id");
				final Date showDate = resultSet.getDate("show_date");
				final Time showTime = resultSet.getTime("show_time");
				final int availablePlaces = resultSet.getInt("available_places");
				final float showPrice = resultSet.getInt("show_price");
				
	        	show = new Show(showId, movieId, roomId, showDate, showTime, availablePlaces, showPrice);
	        	}
	        return show;
			   }
	    }catch (final SQLException e) {
	    	throw new ServiceException("Something went terribly wrong while retrieving the first date.", e);
	    }
	}


	public boolean isUniqueUser(String user) {
		String SQL = "SELECT username FROM customer where username=?";
		try (final Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedstatement = connection.prepareStatement(SQL);
			preparedstatement.setString(1, user);
			ResultSet resultSet = preparedstatement.executeQuery();

			if (!resultSet.next()) {
				return true;
			} else {
				return false;
			}

		} catch (final SQLException e) {
			throw new ServiceException("Something went terribly wrong while retrieving the username.", e);
		}

	}
}