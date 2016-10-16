package nl.cerios.cerioscoop.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.cerios.cerioscoop.ValueObjects.ShowsPresentationVO;
import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.domain.Show;
import nl.cerios.cerioscoop.service.DataAccessObject;
import nl.cerios.cerioscoop.service.GeneralService;
import nl.cerios.cerioscoop.service.MovieNotFoundException;
import nl.cerios.cerioscoop.util.DateUtils;

/**
 * Servlet implementation class NowShowingServlet
 */
@WebServlet("/NowShowingServlet")
public class NowShowingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB // call DB
	private GeneralService generalService;

	@EJB // call DB
	private DataAccessObject dao;

	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final DateUtils dateUtils = new DateUtils();
		final List<Show> shows = dao.getShowsForToday();
		final List<Movie> movies = dao.getMovies();
		List<ShowsPresentationVO> todaysShowsTable = new ArrayList<ShowsPresentationVO>();
		
		shows.sort((itemL, itemR) -> {
			int compare = itemL.getShowDate().compareTo(itemR.getShowDate());
			if (compare == 0) {
				compare = itemL.getShowTime().compareTo(itemR.getShowTime());
			}
			return compare;
		});
		
		try {
			todaysShowsTable = generalService.generateShowTable(shows, movies);
			System.out.println(todaysShowsTable.size());
			request.setAttribute("todaysShowsTable", todaysShowsTable);
		} catch (MovieNotFoundException e) {
			e.printStackTrace();
		}
		
		final Show firstShowing = generalService.getFirstShowforToday(shows);
		if (firstShowing != null) {
			// Third object in red box
			final Date showDateTime = dateUtils.toDateTime(firstShowing.getShowDate(),firstShowing.getShowTime());
			final String countdown = dateUtils.calculateTime(dateUtils.getSecondsBetween(showDateTime, dateUtils.getCurrentDate()));

			// Objects to sent to the now-showing.jsp
			try {
				request.setAttribute("first_movie_today", generalService.getMovieByMovieId(firstShowing.getMovieId(), movies).getTitle());
			} catch (MovieNotFoundException e) {
				e.printStackTrace();
			}
			request.setAttribute("countdown", countdown);
		}
		request.setAttribute("todays_date", dateUtils.getDate());
		// route to jsp
		getServletContext().getRequestDispatcher("/jsp/today-showing.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
