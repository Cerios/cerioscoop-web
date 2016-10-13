package nl.cerios.cerioscoop.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.cerios.cerioscoop.domain.Transaction;
import nl.cerios.cerioscoop.domain.User;
import nl.cerios.cerioscoop.service.CustomerDaoImpl;

/**
 * Servlet implementation class TransactionServlet
 */
@WebServlet("/TicketHistoryServlet")
public class TicketHistoryServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(TicketHistoryServlet.class);

	@EJB
	private CustomerDaoImpl customerDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Transaction> transactions = new ArrayList<Transaction>();
		final HttpSession session = request.getSession();
		User customer = (User) session.getAttribute("user");
		if (customer != null) {
			LOG.debug(customer.toString());
			String a = customer.getUsername();
			LOG.debug(a);
			transactions = customerDao.getTransactionByUsername(a);
			request.setAttribute("transactions", transactions);
			getServletContext().getRequestDispatcher("/jsp/ticket-history.jsp").forward(request, response);
		} else {
			getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
