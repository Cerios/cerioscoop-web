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

import nl.cerios.cerioscoop.domain.Customer;
import nl.cerios.cerioscoop.domain.User;
import nl.cerios.cerioscoop.service.GeneralService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private GeneralService generalService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("successfulRegistry", "");
		request.setAttribute("successfulLogin", "");
		getServletContext().getRequestDispatcher("/jsp/customer.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final List<Customer> dbCustomers = generalService.getCustomers();
		final User customer = new Customer();
		final User authenticatedCustomer;
		final HttpSession session = request.getSession();
		final PrintWriter out = response.getWriter();
		String successfulLogin = "Welcome, you have been logged in successfully";

		// input
		customer.setUsername(request.getParameter("txtUserName"));
		customer.setPassword(request.getParameter("txtPassword"));

		// output
		authenticatedCustomer = generalService.authenticateCustomer(customer, dbCustomers);

		response.setContentType("text/html;charset=UTF-8");

		if (authenticatedCustomer == null) {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Combination username and password do not match!');");
			out.println("location='index.jsp';");
			out.println("</script>");
		} else if (generalService.authenticateUser(authenticatedCustomer)) {
			session.setAttribute("user", authenticatedCustomer);
			session.setAttribute("usertype", "customer");
			request.setAttribute("successfulRegistry", "");
			request.setAttribute("successfulLogin", successfulLogin);
			getServletContext().getRequestDispatcher("/jsp/customer.jsp").forward(request, response);
		} else
			return;
	}
}
