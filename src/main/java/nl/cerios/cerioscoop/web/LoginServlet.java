package nl.cerios.cerioscoop.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.cerios.cerioscoop.dao.CustomerDaoImpl;
import nl.cerios.cerioscoop.domain.Customer;
import nl.cerios.cerioscoop.domain.User;
import nl.cerios.cerioscoop.service.ShowService;
import nl.cerios.cerioscoop.service.SecurityService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private ShowService showService;
	
	@EJB
	private CustomerDaoImpl customerDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("successfulRegistry", "");
		request.setAttribute("successfulLogin", "");
		getServletContext().getRequestDispatcher("/jsp/customer.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final List<Customer> dbCustomers = customerDao.getCustomers();
		final User customer = new Customer(); //TODO make it customer datatype instead of user 
		final User authenticatedCustomer;
		final HttpSession session = request.getSession();
		final PrintWriter out = response.getWriter();
		String successfulLogin = "Welcome, you have been logged in successfully";

		// input
		customer.setUsername(request.getParameter("txtUserName"));
		customer.setPassword(SecurityService.hashPassword(request.getParameter("txtPassword")));
		

		// output
		authenticatedCustomer = showService.authenticateCustomer(customer, dbCustomers);

		response.setContentType("text/html;charset=UTF-8");

		if (authenticatedCustomer == null) {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Combination username and password do not match!');");
			out.println("location='index.jsp';");
			out.println("</script>");
		} else if (showService.authenticateUser(authenticatedCustomer)) {
			session.setAttribute("user", authenticatedCustomer);
			session.setAttribute("usertype", "customer");
			request.setAttribute("successfulRegistry", "");
			request.setAttribute("successfulLogin", successfulLogin);
			getServletContext().getRequestDispatcher("/jsp/customer.jsp").forward(request, response);
		} else
			return;
	}
}
