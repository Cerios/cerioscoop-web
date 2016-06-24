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

import nl.cerios.cerioscoop.domain.Show;
import nl.cerios.cerioscoop.service.ShowService;
import nl.cerios.cerioscoop.util.DateUtils;

/**
 * Servlet implementation class AddShowServlet
 */
@WebServlet("/AddShowServlet")
public class AddShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DateUtils dateUtils = new DateUtils();
	private static ShowService showService = new ShowService();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddShowServlet() {
        super();
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
		Show show = new Show();
		final DateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy"); 					// current format

		if ("Submit".equals(request.getParameter("submitit"))) {
			show.setFilmId(Integer.parseInt(request.getParameter("filmID")));
			show.setRoomId(Integer.parseInt(request.getParameter("roomID")));	
			show.setPremiereTime(dateUtils.getCurrentSqlTime());							//TODO remove and input timepicker
			show.setLastShowingTime(dateUtils.getCurrentSqlTime());							//TODO remove and input timepicker
			try {
				show.setPremiereDate(new java.sql.Date(dateformat.parse(request.getParameter("premieredate")).getTime()));
//				showing.setPremiereTime(new java.sql.Time(dateformat.parse(request.getParameter("premieredate")).getTime()));
				show.setLastShowingDate(new java.sql.Date(dateformat.parse(request.getParameter("lastshowingdate")).getTime()));
//				showing.setLastShowingTime(new java.sql.Time(dateformat.parse(request.getParameter("lastshowingdate")).getTime()));
			} catch (ParseException e) {
				throw new ShowException("Something went wrong while parsing premiere datum.", e);
			}
			showService.addShow(show);
		}
		doGet(request, response);
	}

}
