package nl.cerios.cerioscoop.ValueObjects;

import java.util.List;

import nl.cerios.cerioscoop.domain.Movie;

/**
 * ShowsPresentationVO is a Value Object or Data Transfer Object.
 * http://stackoverflow.com/questions/6986032/difference-between-value-object-pattern-and-data-transfer-pattern
 * 
 * @author rsanders
 *
 */
public class ShowsPresentationVO {
	public Movie movie;
	public List<ShowPresentationVO> shows;

	public ShowsPresentationVO() {
		
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	public List<ShowPresentationVO> getShowsPresentationVO() {
		return shows;
	}

	public void setShowsPresentationVO(List<ShowPresentationVO> show) {
		this.shows = show;
	}
}
