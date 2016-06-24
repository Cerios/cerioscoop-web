package nl.cerios.cerioscoop.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.cerios.cerioscoop.service.ShowService;

/**
 * Servlet implementation class AddFilmServlet
 */
@WebServlet("/AddFilmServlet")
public class AddFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final ShowService SHOWINGSERVICE = new ShowService();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFilmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		if ("Submit".equals(request.getParameter("submitit"))) {
			final int minutes = Integer.parseInt(request.getParameter("minutes"));
			final int movieType = Integer.parseInt(request.getParameter("type"));
			final String filmname = (request.getParameter("filmname"));
			final String language = request.getParameter("language");		
			
			SHOWINGSERVICE.addFilm(filmname, minutes, movieType, language);
		}
		doGet(request, response);
	}
}
