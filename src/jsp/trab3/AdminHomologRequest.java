package jsp.trab3;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.trab3.dao.BookingDAO;
import jsp.trab3.model.BookingModel;
import jsp.trab3.model.DateTimeModel;
import jsp.trab3.model.LabModel;

/**
 * Servlet implementation class AdminHomologRequest
 */
@WebServlet("/AdminHomologRequest")
public class AdminHomologRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminHomologRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminHomologBean brb = new AdminHomologBean();
		brb.setUserName((String) request.getSession().getAttribute("user"));
		String dateString = request.getParameter("date");
		if (dateString != null) {
			brb.setDateTime(dateString);
		} else {
			Date today = new Date();
			SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");
			brb.setDateTime(ft.format(today));
		}
		request.setAttribute("adminHomologBean", brb);
		Calendar c = Calendar.getInstance();
		String dateStr = request.getParameter("date");
		String[] date = null;
		if (dateStr == null)
		{
			Date today = new Date();
			SimpleDateFormat ftA = new SimpleDateFormat ("dd-MM-yyyy");
			dateStr = (ftA.format(today)) + "/" + c.get(Calendar.DAY_OF_WEEK);
			
		}
	    date = dateStr.split("/");
		
		// Fazer requisição de horários
		LabModel lab = new LabModel();
		lab.setName("LabGrad");
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		
		
		try {
			c.setTime(sdf1.parse(date[0]));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.DATE, - Integer.parseInt(date[1])+1);  // number of days to add
		String startWeek = sdf2.format(c.getTime());

		ArrayList<ArrayList<BookingModel>> bookList = BookingDAO.getWeek(lab, startWeek);
		
		String table = brb.getTable();
		
	    String[] wdays;
		wdays = new String[7];
		wdays[1] = "seg";
		wdays[2] = "ter";
		wdays[3] = "qua";
		wdays[4] = "qui";
		wdays[5] = "sex";
		wdays[6] = "sab";
		wdays[0] = "dom";
		
		int i = 0;
		for (ArrayList<BookingModel> dayBook : bookList) {
			for (BookingModel book : dayBook) {
				// Horarios
				
				ArrayList<DateTimeModel> bookings =  (ArrayList<DateTimeModel>) book.getDateTimes();
				
				for (DateTimeModel dateTime : bookings) {
					String time = dateTime.getTime().toString();
					String[] split = time.split(":");
					if (split[0].charAt(0) == '0')
						split[0] = split[0].substring(1);					
						
					table = table.replaceFirst("data-booking='"+wdays[i]+ '-' +split[0]+ "'>", "data-bookingid='"+ Integer.toString(book.getId()) + "' data-booking='"+wdays[i]+ '-' +split[0]+ "'>" + book.getUser().getLogin() );
					
				}				
			}
			i++;
		}
		
		brb.setTable(table);
		request.setAttribute("adminHomologBean", brb);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/adminHomologRequest.jsp");
		dispatcher.forward(request, response);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		AdminHomologBean brb = new AdminHomologBean();
		brb.setUserName((String) request.getSession().getAttribute("user"));
		String dateString = request.getParameter("date");
		if (dateString != null) {
			brb.setDateTime(dateString);
		} else {
			Date today = new Date();
			SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");
			brb.setDateTime(ft.format(today));
		}
		request.setAttribute("adminHomologBean", brb);
		Calendar c = Calendar.getInstance();
		String dateStr = request.getParameter("date");
		String[] date = null;
		if (dateStr == null)
		{
			Date today = new Date();
			SimpleDateFormat ftA = new SimpleDateFormat ("dd-MM-yyyy");
			dateStr = (ftA.format(today)) + "/" + c.get(Calendar.DAY_OF_WEEK);
			
		}
	    date = dateStr.split("/");
		
		// Fazer requisição de horários
		LabModel lab = new LabModel();
		lab.setName("LabGrad");
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		
		
		try {
			c.setTime(sdf1.parse(date[0]));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.DATE, - Integer.parseInt(date[1])+1);  // number of days to add
		String startWeek = sdf2.format(c.getTime());

		ArrayList<ArrayList<BookingModel>> bookList = BookingDAO.getWeek(lab, startWeek);
		
		String table = brb.getTable();
		
	    String[] wdays;
		wdays = new String[7];
		wdays[1] = "seg";
		wdays[2] = "ter";
		wdays[3] = "qua";
		wdays[4] = "qui";
		wdays[5] = "sex";
		wdays[6] = "sab";
		wdays[0] = "dom";
		
		int i = 0;
		for (ArrayList<BookingModel> dayBook : bookList) {
			for (BookingModel book : dayBook) {
				// Horarios
				
				ArrayList<DateTimeModel> bookings =  (ArrayList<DateTimeModel>) book.getDateTimes();
				
				for (DateTimeModel dateTime : bookings) {
					String time = dateTime.getTime().toString();
					String[] split = time.split(":");
					if (split[0].charAt(0) == '0')
						split[0] = split[0].substring(1);					
						
					table = table.replaceFirst("data-booking='"+wdays[i]+ '-' +split[0]+ "'>", "data-bookingid='"+ Integer.toString(book.getId()) + "' data-booking='"+wdays[i]+ '-' +split[0]+ "'>" + book.getUser().getLogin() );
					
				}				
			}
			i++;
		}
		
		brb.setTable(table);
		request.setAttribute("adminHomologBean", brb);
		
		
		System.out.println(request.getParameter("bookID"));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/adminHomologRequest.jsp");
		dispatcher.forward(request, response);
		
		
	}

}
