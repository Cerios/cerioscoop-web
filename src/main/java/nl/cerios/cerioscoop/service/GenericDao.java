package nl.cerios.cerioscoop.service;

import java.util.List;

import javax.ejb.Remote;

import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.domain.Show;

@Remote
public interface GenericDao {
	public List<Movie> getMovies();
	public Movie getMovieByMovieId(int movieId);
	public List<Show> getShows();
	public List<Show> getShowsForToday();
	public Show getShowById(int showid);
	
}
