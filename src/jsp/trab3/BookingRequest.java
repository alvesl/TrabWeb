package jsp.trab3;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.trab3.dao.BookingDAO;
import jsp.trab3.dao.LabDAO;
import jsp.trab3.model.BookingModel;
import jsp.trab3.model.DateTimeModel;
import jsp.trab3.model.LabModel;
import jsp.trab3.model.UserModel;

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
		int soma = 0;
		BookingRequestBean brb = new BookingRequestBean();
		brb.setUserName((String) request.getSession().getAttribute("user"));
		brb.setUserId(Integer.parseInt((String) request.getSession().getAttribute("userId")));
		
		Calendar c = Calendar.getInstance();
		String dateString = request.getParameter("date");
		if (dateString == null) {
			Date today = new Date();
			SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");
			dateString = (ft.format(today)) + "/" + c.get(Calendar.DAY_OF_WEEK);
			soma = 1;
		}
		
		String[] date = dateString.split("/");
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		
		
		try {
			c.setTime(sdf1.parse(date[0]));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.DATE, - Integer.parseInt(date[1]) + soma);  // number of days to add
		String startWeek = sdf2.format(c.getTime());
		
		LabModel lab = null;
	    if (date.length <= 2) {
	    	lab = LabDAO.getList().get(0);
			if (lab == null) {
				lab = new LabModel();
				lab.setName("");
			}
	    } else {
	    	lab = new LabModel();
	    	lab.setName(date[2]);
	    }
		
	    request.getSession().setAttribute("lab", lab.getName());
	    request.getSession().setAttribute("current_date", date[0]);
	    
	    
		ArrayList<ArrayList<BookingModel>> bookList = BookingDAO.getWeekByUser(lab, startWeek, brb.getUserId());
		
		String table = brb.getTable();
		
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
					
					System.out.println(table.indexOf("data-booking='"+ brb.getWdays()[i] + '-' +split[0]+ "'><i class='icon-plus' />"));
					table = table.replaceFirst("data-booking='"+ brb.getWdays()[i] + '-' +split[0]+ "'><i class='icon-plus' />", "data-booking='"+brb.getWdays()[i]+ '-' +split[0]+ "'>" + book.getUser().getLogin() );
					
				}				
			}
			i++;
		}
		
		ArrayList<LabModel> labList = new ArrayList<LabModel>();
		labList.addAll(LabDAO.getList());
		LabModel auxlab = null;
		for (LabModel labM : labList) {
			if (labM.getName().equals(lab.getName()))
				auxlab = (labM);
		}
		labList.remove(auxlab);
		labList.add(0, lab);
		
		brb.setDateTime(date[0]);
		brb.setTable(table);
		
		request.setAttribute("labs", labList);
		request.setAttribute("bookingRequestBean", brb);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/bookingRequest.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookingModel bm = new BookingModel();
		UserModel u = new UserModel();
		u.setId(Integer.parseInt((String) request.getSession().getAttribute("userId")));
		bm.setUser(u);
		String labName = (String)request.getSession().getAttribute("lab");
		LabModel lab = LabDAO.getLab(labName);		
		bm.setLab(lab);
		
		bm.setMotivo(request.getParameter("motivo"));
		bm.setResponsavel(request.getParameter("responsavel"));
		bm.setDescricao(request.getParameter("descricao"));
		bm.setProjeto(request.getParameter("projeto"));
		bm.setDateTimes(getDateTimesFromRequest(request));
		
		BookingDAO.insert(bm);
		
		ConfirmationBean bean = new ConfirmationBean();
		bean.setUserName((String) request.getSession().getAttribute("user"));
		bean.setBookingModel(bm);
		request.setAttribute("confirmationBean", bean);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/reservaConfirmada.jsp");
		dispatcher.forward(request, response);
	}
	
	private List<DateTimeModel> getDateTimesFromRequest(HttpServletRequest request){
		List<DateTimeModel> to_return = new ArrayList<DateTimeModel>();
		String dateString = (String) request.getParameter("date_times");
		String[] dateArray = dateString.split("/");
		
		String current_date = (String)request.getSession().getAttribute("current_date");
		Calendar c = Calendar.getInstance();
		SimpleDateFormat stf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat stf1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		SimpleDateFormat stf2 = new SimpleDateFormat("hh:mm:ss");
		try {
			c.setTime(stf.parse(current_date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.add(Calendar.DATE, -c.get(Calendar.DAY_OF_WEEK));
		Hashtable<String, String> hash = new Hashtable<String, String>();
		
		hash.put("domingo", stf.format(c.getTime()));
		c.add(Calendar.DATE, 1);
		hash.put("segunda", stf.format(c.getTime()));
		c.add(Calendar.DATE, 1);
		hash.put("terca", stf.format(c.getTime()));
		c.add(Calendar.DATE, 1);
		hash.put("quarta", stf.format(c.getTime()));
		c.add(Calendar.DATE, 1);
		hash.put("quinta", stf.format(c.getTime()));
		c.add(Calendar.DATE, 1);
		hash.put("sexta", stf.format(c.getTime()));
		c.add(Calendar.DATE, 1);
		hash.put("sabado", stf.format(c.getTime()));
		
		for(int i = 1 ; i < dateArray.length ; i++) {
			String[] s = dateArray[i].split("-");
			String hora = s[1] + ":00:00";
			String dia = hash.get(s[0]) + " 00:00:00";
			
			DateTimeModel dt = new DateTimeModel();
			try {
				dt.setDate(new java.sql.Date(stf.parse(dia).getTime()));
				dt.setTime(new java.sql.Time(stf2.parse(hora).getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			to_return.add(dt);
		}
		
		return to_return;
	}

}
