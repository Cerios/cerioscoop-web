package nl.cerios.cerioscoop.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.cerios.cerioscoop.domain.Showing;
import nl.cerios.cerioscoop.service.ShowingService;
import nl.cerios.cerioscoop.util.DateUtils;

/**
 * Servlet implementation class AddShowingServlet
 */
@WebServlet("/AddShowingServlet")
public class AddShowingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DateUtils dateUtils = new DateUtils();
	private static ShowingService showingService = new ShowingService();   
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
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		Showing showing = new Showing();
		final DateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy"); 					// current format

		if ("Submit".equals(request.getParameter("submitit"))) {
			showing.setFilmId(Integer.parseInt(request.getParameter("filmID")));
			showing.setRoomId(Integer.parseInt(request.getParameter("roomID")));	
			showing.setPremiereTime(dateUtils.getCurrentSqlTime());							//TODO remove and input timepicker
			showing.setLastShowingTime(dateUtils.getCurrentSqlTime());						//TODO remove and input timepicker
			try {
				showing.setPremiereDate(new java.sql.Date(dateformat.parse(request.getParameter("premieredate")).getTime()));
//				showing.setPremiereTime(new java.sql.Time(dateformat.parse(request.getParameter("premieredate")).getTime()));
				showing.setLastShowingDate(new java.sql.Date(dateformat.parse(request.getParameter("lastshowingdate")).getTime()));
//				showing.setLastShowingTime(new java.sql.Time(dateformat.parse(request.getParameter("lastshowingdate")).getTime()));
			} catch (ParseException e) {
				throw new ShowingException("Something went wrong while parsing premiere datum.", e);
			}
			showingService.addShowing(showing);
		}
		doGet(request, response);
	}

}
