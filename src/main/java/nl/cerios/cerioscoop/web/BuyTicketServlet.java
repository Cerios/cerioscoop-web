package nl.cerios.cerioscoop.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.cerios.cerioscoop.domain.Show;
import nl.cerios.cerioscoop.service.GenericDaoImpl;


/**
 * Servlet implementation class BuyTicketServlet
 */
@WebServlet("/BuyTicketServlet")
public class BuyTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	GenericDaoImpl genericDaoImpl;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("showId", "");
		
		request.getRequestDispatcher("/jsp/buy-ticket.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String showId = request.getParameter("showId");
		Show show = genericDaoImpl.getShowById(Integer.parseInt(showId));
		
		
		request.setAttribute("show", show);
		
		request.getRequestDispatcher("/jsp/buy-ticket.jsp").forward(request, response);
	}

}
