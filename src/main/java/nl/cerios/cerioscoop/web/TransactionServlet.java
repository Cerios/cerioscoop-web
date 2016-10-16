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

import nl.cerios.cerioscoop.domain.Transaction;
import nl.cerios.cerioscoop.domain.User;
import nl.cerios.cerioscoop.service.DataAccessObject;

/**
 * Servlet implementation class TransactionServlet
 */
@WebServlet("/TransactionServlet")
public class TransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB // call DB
	private DataAccessObject dao;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		final HttpSession session = request.getSession();
		User customer = (User) session.getAttribute("user");
		if(customer !=null) {
		System.out.println(customer);
		String a = customer.getUsername();
		System.out.println(a);
		transactions = dao.getTransactionByUsername(a);
		request.setAttribute("transactions", transactions);
		getServletContext().getRequestDispatcher("/jsp/transactions.jsp").forward(request, response);
		}
		else{getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
