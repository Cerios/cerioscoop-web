package nl.cerios.cerioscoop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import nl.cerios.cerioscoop.domain.Customer;
import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.domain.Show;
import nl.cerios.cerioscoop.domain.User;
import nl.cerios.cerioscoop.util.DateUtils;
import nl.cerios.cerioscoop.valueobjects.ShowPresentationVO;
import nl.cerios.cerioscoop.valueobjects.ShowsPresentationVO;

@Stateless										//Stateless is de status van de gevulde opjecten. Best Practice is stateless.
public class GeneralService {
	
	@EJB
	private GenericDaoImpl genericDao;
	
	/**
	 * Returns a first showing record.
	 * 
	 * @return firstShowing
	 */
	public Show getFirstShowforToday(final List<Show> listOfShows){
		Show firstShow = null;	
		for (final Show show : listOfShows) {
			if(DateUtils.toDateTime(show.getShowDate(), show.getShowTime()).after(DateUtils.getCurrentSqlTime())){	
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
	
	public List<ShowsPresentationVO> createTodaysShowsTable(){
		final List<Show> shows = genericDao.getShowsForToday();
		final List<Movie> movies = genericDao.getMovies();
		List<ShowsPresentationVO> todaysShowsTable = null;
		
		shows.sort((itemL, itemR) -> {
			int compare = itemL.getShowDate().compareTo(itemR.getShowDate());
			if (compare == 0) {
				compare = itemL.getShowTime().compareTo(itemR.getShowTime());
			}
			return compare;
		});
		
		try {
			todaysShowsTable = generateShowTable(shows, movies);
		} catch (MovieNotFoundException e) {
			e.printStackTrace();//TODO: Technische melding van de db-error dat de movie niet gevonden kan worden
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


