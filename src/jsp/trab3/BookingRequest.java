package jsp.trab3;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookingRequest
 */
@WebServlet("/BookingRequest")
public class BookingRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookingRequestBean brb = new BookingRequestBean();
		brb.setUserName((String) request.getSession().getAttribute("user"));
		String dateString = request.getParameter("date");
		if (dateString != null) {
			brb.setDateTime(dateString);
		} else {
			Date today = new Date();
			SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");
			brb.setDateTime(ft.format(today));
		}
		request.setAttribute("bookingRequestBean", brb);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/bookingRequest.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
