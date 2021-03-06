package jsp.trab3;

import java.io.IOException;
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
import jsp.trab3.dao.LabDAO;
import jsp.trab3.dao.UserDAO;
import jsp.trab3.model.BookingModel;
import jsp.trab3.model.DateTimeModel;
import jsp.trab3.model.LabModel;
import jsp.trab3.model.UserModel;

/**
 * Servlet implementation class AdminHomologRequest
 */
@WebServlet("/AdminHomologRequest")
public class AdminHomologRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String lastClickedBook;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminHomologRequest() {
        super();
        // TODO Auto-generated constructor stub
        lastClickedBook = null;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int soma = 0;
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
		
		Calendar c = Calendar.getInstance();
		String dateStr = request.getParameter("date");
		String[] date = null;
		if (dateStr == null)
		{
			Date today = new Date();
			SimpleDateFormat ftA = new SimpleDateFormat ("dd-MM-yyyy");
			dateStr = (ftA.format(today)) + "/" + c.get(Calendar.DAY_OF_WEEK);
			soma = 1;
		}
	    date = dateStr.split("/");
	    
	  
	    brb.setDateTime(date[0]);
		// Fazer requisi��o de hor�rios
	    LabModel lab = null;
	    if (date.length <= 2) {
	    	lab = LabDAO.getList().get(0);
			if (lab == null)
				lab.setName("");
	    } else {
	    	lab = new LabModel();
	    	lab.setName(date[2]);
	    }
		

		
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		
		
		try {
			c.setTime(sdf1.parse(date[0]));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.DATE, - Integer.parseInt(date[1])+soma);  // number of days to add
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
					String color = book.isHomolog() ?  "btn-danger" : "btn-success" ; 	
					table = table.replaceFirst("data-booking='"+wdays[i]+ '-' +split[0]+ "'>", "class='" + color + "'" +   " data-bookingid='"+ Integer.toString(book.getId()) + "' data-booking='"+wdays[i]+ '-' +split[0]+ "'>" + book.getUser().getLogin() );
					
				}				
			}
			i++;
		}
		
		brb.setTable(table);

		request.setAttribute("adminHomologBean", brb);

		
		ArrayList<LabModel> labList = new ArrayList<LabModel>();
		labList.addAll(LabDAO.getList());
		LabModel auxlab = null;
		for (LabModel labM : labList) {
			if (labM.getName().equals(lab.getName()))
				auxlab = (labM);
		}
		labList.remove(auxlab);
		labList.add(0, lab);
		
		request.setAttribute("labs", labList);
		request.setAttribute("adminHomologBean", brb);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/adminHomologRequest.jsp");
		dispatcher.forward(request, response);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int soma = 0;
		
		BookingModel bm = new BookingModel();

		
		if (request.getParameter("bookID") != null) {
			
			String bId = request.getParameter("bookID");
			
			if (request.getParameter("bookEmpty") != null)
				bId = lastClickedBook;
			else
				lastClickedBook = request.getParameter("bookID"); 
			
			 bm = BookingDAO.getBooking(bId);
			 
			
			ArrayList<SelectedBookingBean> selected = new ArrayList<SelectedBookingBean>();
			lastClickedBook = request.getParameter("bookID"); 
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			
			for (DateTimeModel dtm : bm.getDateTimes()) {
				SelectedBookingBean sbb = new SelectedBookingBean();
				sbb.setTime(dtm.getTime().toString());
				sbb.setDate(sdf.format(dtm.getDate()));
				selected.add(sbb);
			}
			 
			
			request.setAttribute("selectedBookingBean", selected);
			request.setAttribute("selectedBooking", bm);
		}
		
		//Deferir ou indeferir
		String acao = request.getParameter("acao");
		if (acao != null ) {
			if (acao.equals("Defere")) {
				if (bm != null) {
					// houve clique
					//Verificar se o cara j� foi homologado
					if (bm.isHomolog()) {
						BookingDAO.homolog(bm.getId(), true, request.getParameter("obs"));
					//E-mail
						UserModel usr ;
						usr = bm.getUser();
						bm = BookingDAO.getBooking(Integer.toString(bm.getId()));
						Mail.sendMail(usr.getEmail(), "Pedido de sala", "Seu pedido para a sala "+ bm.getLab().getName()+ " foi DEFERIDO! \n Motivo: "+bm.getDeferedObs(), "Pedidos@Sala.com");
						
						
					}
				}
			} else {
				if (bm != null) {
					if (bm.isHomolog()) {
						BookingDAO.homolog(bm.getId(), false, request.getParameter("obs"));
						
						// Email
						UserModel usr ;
						usr = bm.getUser();
						bm = BookingDAO.getBooking(Integer.toString(bm.getId()));
						Mail.sendMail(usr.getEmail(), "Pedido de sala", "Seu pedido para a sala "+ bm.getLab().getName()+ " foi INDEFERIDO! \n Motivo: " +bm.getDeferedObs(), "Pedidos@Sala.com");
					}
					
				}
				
				
			}
			
		}
		
		
		AdminHomologBean brb = new AdminHomologBean();
		String dateStr = request.getParameter("datePost");
		Calendar c = Calendar.getInstance();
		
		if (dateStr == null) {
			
			Date today = new Date();
			SimpleDateFormat ftA = new SimpleDateFormat ("dd-MM-yyyy");
			dateStr = (ftA.format(today)) + "/" + c.get(Calendar.DAY_OF_WEEK);
			soma = 1;
		}
		
		
		
		String[] date = null;

	    date = dateStr.split("/");
		
	    brb.setDateTime(date[0]);
	    
	    
	    
		// Fazer requisi��o de hor�rios
		String l = request.getParameter("labName");
	    
	    LabModel lab = new LabModel();
		lab.setName(l);
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		
		
		try {
			c.setTime(sdf1.parse(date[0]));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.DATE, - Integer.parseInt(date[1])+soma);  // number of days to add
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
					String color = book.isHomolog() ?   "btn-danger" : "btn-success"  ; 
					table = table.replaceFirst("data-booking='"+wdays[i]+ '-' +split[0]+ "'>", "class='" + color + "'" +  " data-bookingid='"+ Integer.toString(book.getId()) + "' data-booking='"+wdays[i]+ '-' +split[0]+ "'>" + book.getUser().getLogin() );
					
				}				
			}
			i++;
		}
		
		brb.setTable(table);
		request.setAttribute("adminHomologBean", brb);
		

		
		ArrayList<LabModel> labList = new ArrayList<LabModel>();
		labList.addAll(LabDAO.getList());
		LabModel auxlab = null;
		if (lab.getName().endsWith(" "))
			lab.setName(lab.getName().substring(0, lab.getName().length()-1));
		for (LabModel labM : labList) {
			if (labM.getName().equals(lab.getName()))
				auxlab = (labM);
		}
		labList.remove(auxlab);
		labList.add(0, lab);
		
		request.setAttribute("labs", labList);
		
		request.setAttribute("adminHomologBean", brb);
		

		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/adminHomologRequest.jsp");
		dispatcher.forward(request, response);
		
		
	}

}
