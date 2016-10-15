package nl.cerios.cerioscoop.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.cerios.cerioscoop.domain.Movie;
import nl.cerios.cerioscoop.service.DataAccessObject;

/**
 * Servlet implementation class MovieServlet
 */
@WebServlet("/MoviePresentationServlet")
public class MoviePresentationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB // call DB
	private DataAccessObject dao;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final int movieId = Integer.parseInt(request.getParameter("movieId"));
		final Movie movie = dao.getMovieByMovieId(movieId);
		request.setAttribute("movie", movie);

		getServletContext().getRequestDispatcher("/jsp/movie-presentation.jsp").forward(request, response);
	}

}
