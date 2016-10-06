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
import nl.cerios.cerioscoop.helper.ErrorMessage;
import nl.cerios.cerioscoop.helper.RegisterAttributes;
import nl.cerios.cerioscoop.service.CustomerDaoImpl;
import nl.cerios.cerioscoop.service.CustomerService;
import nl.cerios.cerioscoop.service.GeneralService;
import nl.cerios.cerioscoop.service.SecurityService;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final int NAME_MIN_SIZE = 8;
	private static final int NAME_MAX_SIZE = 20;
	private static final String FIRSTNAME_VALIDATION_MESSAGE = "Invalid firstname: min "+NAME_MIN_SIZE+" / max "+NAME_MAX_SIZE+" alfanumeric characters";
	private static final String LASTNAME_VALIDATION_MESSAGE = "Invalid lastname: min "+NAME_MIN_SIZE+" / max "+NAME_MAX_SIZE+" alfanumeric characters";
	private static final String USERNAME_VALIDATION_MESSAGE = "Invalid Username: min "+NAME_MIN_SIZE+" / max "+NAME_MAX_SIZE+" alfanumeric characters";
	
	private static final int PASSWORD_MIN_SIZE = 6;
	private static final int PASSWORD_MAX_SIZE = 12;
	private static final String PASSWORD_VALIDATION_MESSAGE = "Invalid username/password combination";
	
	private static final int EMAIL_MIN_SIZE = 6;
	private static final String EMAIL_VALIDATION_MESSAGE = "Enter valid email (min 6 characters)";

	@EJB
	private GeneralService generalService;
	
	@EJB
	private CustomerDaoImpl customerDao;

	@EJB
	private CustomerService customerService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RegisterAttributes registerAttributes = new RegisterAttributes();
		registerAttributes.setFirstname("");
		registerAttributes.setLastname("");
		registerAttributes.setUsername("");
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
		registerAttributes.setUsername(request.getParameter("username"));
		registerAttributes.setEmail(request.getParameter("email"));
		
		// Is it a valid firstname?
		final String firstname = request.getParameter("firstname");
		if (alfanumeric.matcher(firstname).find() && theRightSize(firstname, NAME_MIN_SIZE, NAME_MAX_SIZE)) {
			customer.setFirstName(firstname);
		} else {
			errorMessage.setFirstnameError(FIRSTNAME_VALIDATION_MESSAGE); 
		}

		// Is it a valid lastname?
		final String lastname = request.getParameter("lastname");
		if (alfanumeric.matcher(lastname).find() && theRightSize(lastname, NAME_MIN_SIZE, NAME_MAX_SIZE)) {
			customer.setLastName(lastname);
		} else {
			errorMessage.setLastnameError(LASTNAME_VALIDATION_MESSAGE);
		}
		
		// Is it a valid username?
		final String username = request.getParameter("username");
		if (alfanumeric.matcher(username).find() && theRightSize(username, NAME_MIN_SIZE, NAME_MAX_SIZE)) {
			customer.setUsername(username);
		} else {
			errorMessage.setUsernameError(USERNAME_VALIDATION_MESSAGE);
		}
		
		// Is it a valid password?
		final String password = request.getParameter("password");
		final String password2 = request.getParameter("password2");
		if (password.equals(password2) && punctuation.matcher(password).find() && theRightSize(password, PASSWORD_MIN_SIZE, PASSWORD_MAX_SIZE)) {
			customer.setPassword(SecurityService.hashPassword(password));
		} else {
			errorMessage.setPasswordError(PASSWORD_VALIDATION_MESSAGE);
		}

		// Is it a valid e-mail?
		final String email = request.getParameter("email");
		if (email.length() >= EMAIL_MIN_SIZE && email.contains("@")) {
			customer.setEmail(email);
		} else {
			errorMessage.setEmailError(EMAIL_VALIDATION_MESSAGE); 
		}
		
		if (customer.getFirstName() != null && customer.getLastName() != null && customer.getUsername() != null
				&& customer.getPassword() != null && customer.getEmail() != null) {
			customerDao.registerCustomer(customer);
			session.setAttribute("user", customer);
		}

		if (errorMessage.getFirstnameError() != null || errorMessage.getLastnameError() != null
                || errorMessage.getUsernameError() != null || errorMessage.getPasswordError() != null
                || errorMessage.getEmailError() != null) {
			request.setAttribute("errorMessage", errorMessage);
			request.setAttribute("registerAttributes", registerAttributes);
			getServletContext().getRequestDispatcher("/jsp/register.jsp").forward(request, response);
		} else {
			session.setAttribute("user", customer);
			session.setAttribute("usertype", "customer");
			successfulRegistry = "Welcome, your registry has been processed!";
			request.setAttribute("successfulRegistry", successfulRegistry);
			request.setAttribute("successfulLogin", "");
			request.getRequestDispatcher("/jsp/customer.jsp").forward(request, response);
			// change link to the correct page after valid registration
		}
	}
	
	private boolean theRightSize(final String input, final int min, final int max ){
		return (input.length() >= min && input.length() <= max);
	}

}
