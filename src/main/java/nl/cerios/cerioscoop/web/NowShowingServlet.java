package nl.cerios.cerioscoop.web;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.cerios.cerioscoop.service.GeneralService;
import nl.cerios.cerioscoop.valueobjects.ShowsPresentationVO;

/**
 * Servlet implementation class NowShowingServlet
 */
@WebServlet("/NowShowingServlet")
public class NowShowingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private GeneralService generalService;

	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		List<ShowsPresentationVO> todaysShowsTable = generalService.createTodaysShowsTable();
		request.setAttribute("todaysShowsTable", todaysShowsTable);
		
		// route to jsp
		getServletContext().getRequestDispatcher("/jsp/today-showing.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
