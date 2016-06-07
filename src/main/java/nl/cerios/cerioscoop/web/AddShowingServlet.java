package nl.cerios.cerioscoop.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.cerios.cerioscoop.service.ShowingService;
import nl.cerios.cerioscoop.util.DateUtils;

/**
 * Servlet implementation class AddShowingServlet
 */
@WebServlet("/AddShowingServlet")
public class AddShowingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DateUtils DU = new DateUtils();
	private static ShowingService SS = new ShowingService();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddShowingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date date = null;
		Date date2 = null;
		DateFormat from = new SimpleDateFormat("MM/dd/yyyy"); 						// current format
		DateFormat formatTo = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);	// wanted format
		java.sql.Date premiereDate = null;
		java.sql.Time premiereTime = DU.getCurrentSqlTime();	//ToDo
		java.sql.Date lastShowingDate = null;
		java.sql.Time lastShowingTime = DU.getCurrentSqlTime();	//ToDo

		if (request.getParameter("submitit").equals("Submit")) {
			int filmID = Integer.parseInt(request.getParameter("filmID"));
			int roomID = Integer.parseInt(request.getParameter("roomID"));		
			String premieredate = request.getParameter("premieredate");
			String lastshowingdate = request.getParameter("lastshowingdate");
			
			try {
				premieredate = formatTo.format(from.parse(premieredate));
				date = formatTo.parse(premieredate);
				lastshowingdate = formatTo.format(from.parse(lastshowingdate));
				date2 = formatTo.parse(lastshowingdate); 
				premiereDate = new java.sql.Date(date.getTime());  //Maak methode in DateUtils!
				lastShowingDate = new java.sql.Date(date2.getTime());	//Maak methode in DateUtils!
			} catch (ParseException e) {
				throw new FilmShowingServletException("Something went wrong while parsing premiere datum.", e);
				}
			SS.registerShowing(filmID, roomID, premiereDate, premiereTime, lastShowingDate, lastShowingTime);
		}
		doGet(request, response);
	}

}