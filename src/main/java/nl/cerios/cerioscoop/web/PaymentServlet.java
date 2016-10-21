package nl.cerios.cerioscoop.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.cerios.cerioscoop.domain.Customer;
import nl.cerios.cerioscoop.domain.Show;
import nl.cerios.cerioscoop.domain.Transaction;
import nl.cerios.cerioscoop.domain.User;
import nl.cerios.cerioscoop.service.CustomerDaoImpl;
import nl.cerios.cerioscoop.service.CustomerService;
import nl.cerios.cerioscoop.service.GenericDaoImpl;
import nl.cerios.cerioscoop.service.SecurityService;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Show show;
    private int reservedPlaces;
    private float totalPrice;
    private static final Logger LOG = LoggerFactory.getLogger(GenericDaoImpl.class);
	
    @EJB
	private CustomerDaoImpl customerDao;
	
	@EJB
	private GenericDaoImpl genericDao;

	@EJB
	private CustomerService customerService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//TODO check Availableplaces tov reserved_places - capacity;
		//TODO check Minimaal 1 ticket is ingevoerd
		//TODO check Maximum aantal aan tickets dat kan worden gekocht
		
		//Get reserved places from BuyTickets
		reservedPlaces = Integer.parseInt(request.getParameter("reserved_places"));	
		
		int showId = Integer.parseInt(request.getParameter("showId"));
		show = genericDao.getShowById(showId);
		totalPrice = customerService.calculateTotalPrice(show, reservedPlaces);
		request.setAttribute("total_price", totalPrice);
		request.getRequestDispatcher("/jsp/payment-ticket.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final Transaction transaction = new Transaction();
		final PrintWriter out = response.getWriter();
		final HttpSession session = request.getSession();
		final User user = (User) session.getAttribute("user");
		final String bankAccount = request.getParameter("bankaccount") != null ? request.getParameter("bankaccount") : "";
		
		response.setContentType("text/html;charset=UTF-8");
		//reservedChairs = Integer.parseInt(request.getParameter("reserved_places"));
		//show = genericDao.getShowById(1);  //Integer.parseInt(request.getParameter("show_id")
		//totalPrice = customerService.calculateTotalPrice(show, (int) request.getAttribute("reserved_places"));
		LOG.debug("reservedPlaces " + reservedPlaces);
		LOG.debug("bankaccount " + (String) request.getParameter("bankaccount"));
		LOG.debug("firstname " + user.getFirstName());
		
		if (!bankAccount.trim().isEmpty() && SecurityService.ibanTest(bankAccount.trim())) {
			transaction.setBankAccount((String) request.getParameter("bankaccount"));
			
			if (user instanceof Customer){
				transaction.setCustomer((Customer)user);
				LOG.debug("Is customer");
			} else {
				//TODO Throw exception
				LOG.error("Invalid usertype!");
			}
			
			transaction.setReservedChairs(reservedPlaces);
			transaction.setShow(show);
			transaction.setTotalPrice(totalPrice);
			customerDao.addTransaction(transaction);
			
			//TODO update_available_chairs in show_table
			customerDao.updateChairsSold(reservedPlaces, show.getShowId());
			
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Thanks for buying the tickets!');");
			out.println("location='/cerioscoop-web/';");
			out.println("</script>");
		
		}else{			
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Your IBAN is invalid!');");
			out.println("location='/cerioscoop-web/';");
			out.println("</script>");
		}
	}

}
