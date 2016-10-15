package nl.cerios.cerioscoop.web;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.cerios.cerioscoop.domain.Customer;
import nl.cerios.cerioscoop.domain.ErrorMessage;
import nl.cerios.cerioscoop.domain.RegisterAttributes;
import nl.cerios.cerioscoop.service.CustomerService;
import nl.cerios.cerioscoop.service.GeneralService;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private GeneralService generalService;

	@EJB
	private CustomerService customerService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RegisterAttributes registerAttributes = new RegisterAttributes();
		registerAttributes.setFirstname("");
		registerAttributes.setLastname("");
		registerAttributes.setEmail("");
		request.setAttribute("registerAttributes", registerAttributes);
		getServletContext().getRequestDispatcher("/jsp/register.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Customer customer = new Customer();
		Pattern alfanumeric = Pattern.compile("[a-zA-Z0-9]");
		Pattern punctuation = Pattern.compile("[a-zA-Z0-9_?<>.,;:'`]");

		final HttpSession session = request.getSession();
		ErrorMessage errorMessage = new ErrorMessage();
		RegisterAttributes registerAttributes = new RegisterAttributes();
		String successfulRegistry = "";

		registerAttributes.setFirstname(request.getParameter("firstname"));
		registerAttributes.setLastname(request.getParameter("lastname"));
		registerAttributes.setEmail(request.getParameter("email"));
		
		if (request.getParameter("firstname").length() >= 8 && request.getParameter("firstname").length() <= 20
				&& alfanumeric.matcher(request.getParameter("firstname")).find()) {
			customer.setFirstName(request.getParameter("firstname"));
		} else {
			errorMessage.setFirstnameError("Invalid firstname: min 8 / max 20 alfanumeric characters"); 
		}

		if (request.getParameter("lastname").length() >= 8 && request.getParameter("lastname").length() <= 20
				&& alfanumeric.matcher(request.getParameter("lastname")).find()) {
			customer.setLastName(request.getParameter("lastname"));
		} else {
			errorMessage.setLastnameError("Invalid lastname: min 8 / max 20 alfanumeric characters");
		}
		if (request.getParameter("username").length() >= 8 && request.getParameter("username").length() <= 20) {
			customer.setUsername(request.getParameter("username"));
		} else {
			errorMessage.setUsernameError("Username invalid: min 8 / max 20 alfanumeric characters");
		}

		if (request.getParameter("password").length() >= 6 && request.getParameter("password").length() <= 12
				&& request.getParameter("password").equals(request.getParameter("password2"))
				&& punctuation.matcher(request.getParameter("password")).find()) {
			customer.setPassword(request.getParameter("password"));
		} else {
			errorMessage.setPasswordError("Invalid username/password combination (username: min 6 / max 12 characters)");
		}

		if (request.getParameter("email").length() >= 6 && request.getParameter("email").contains("@")) {
			customer.setEmail(request.getParameter("email"));
		} else {

			errorMessage.setEmailError("Enter valid email (min 6 characters)"); 
		}
		if (customer.getFirstName() != null && customer.getLastName() != null && customer.getUsername() != null
				&& customer.getPassword() != null && customer.getEmail() != null) {
			generalService.registerCustomer(customer);
			session.setAttribute("user", customer);
		}

		if (errorMessage.getFirstnameError() != null || errorMessage.getLastnameError() != null
                || errorMessage.getUsernameError() != null || errorMessage.getPasswordError() != null
                || errorMessage.getEmailError() != null) {
			request.setAttribute("errorMessage", errorMessage);
			request.setAttribute("registerAttributes", registerAttributes);
			getServletContext().getRequestDispatcher("/jsp/register.jsp").forward(request, response);
			//request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
		} else {
			if (session != null) {
				session.removeAttribute("user");
				session.removeAttribute("usertype");
			}
			session.setAttribute("user", customer);
			session.setAttribute("usertype", "customer");
			successfulRegistry = "Welcome, your registry has been processed!";
			request.setAttribute("successfulRegistry", successfulRegistry);
			request.setAttribute("successfulLogin", "");
			request.getRequestDispatcher("/jsp/customer.jsp").forward(request, response);
			// change link to the correct page after valid registration
		}
	}

}
