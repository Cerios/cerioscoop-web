package nl.cerios.cerioscoop.web;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.domain.Show;
import nl.cerios.cerioscoop.service.DataAccessObject;
import nl.cerios.cerioscoop.service.GeneralService;
import nl.cerios.cerioscoop.service.MovieNotFoundException;
import nl.cerios.cerioscoop.valueobjects.ShowsPresentationVO;

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
		final List<Show> shows = dao.getShowsForToday();
		final List<Movie> movies = dao.getMovies();
		List<ShowsPresentationVO> todaysShowsTable;
		
		shows.sort((itemL, itemR) -> {
			int compare = itemL.getShowDate().compareTo(itemR.getShowDate());
			if (compare == 0) {
				compare = itemL.getShowTime().compareTo(itemR.getShowTime());
			}
			return compare;
		});
		
		try {
			todaysShowsTable = generalService.generateShowTable(shows, movies);
			request.setAttribute("todaysShowsTable", todaysShowsTable);
		} catch (MovieNotFoundException e) {
			e.printStackTrace();//TODO: Technische melding van de db-error dat de movie niet gevonden kan worden
		}
		// route to jsp
		getServletContext().getRequestDispatcher("/jsp/today-showing.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
