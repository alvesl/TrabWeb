package jsp.trab3;

import java.util.ArrayList;

import jsp.trab3.model.BookingModel;
import jsp.trab3.model.LabModel;

public class BookingRequestBean {
	private String userName;
	private int userId;
	private String dateTime;
	private String table;
	private String[] wdays;
	private ArrayList<ArrayList<BookingModel>> bookings;
	private ArrayList<LabModel> labs;

	public BookingRequestBean() {
		wdays = new String[7];
		wdays[1] = "segunda";
		wdays[2] = "terça";
		wdays[3] = "quarta";
		wdays[4] = "quinta";
		wdays[5] = "sexta";
		wdays[6] = "sábado";
		wdays[0] = "domingo";
		
		table = null;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDateTime() {
		return dateTime;
	}
	
	public int getUserId(){
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String[] getWdays() {
		return wdays;
	}

	public void setWdays(String[] wdays) {
		this.wdays = wdays;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	public ArrayList<ArrayList<BookingModel>> getBookings() {
		return bookings;
	}

	public void setBookings(ArrayList<ArrayList<BookingModel>> bookings) {
		this.bookings = bookings;
	}
	
	public ArrayList<LabModel> getLabs() {
		return labs;
	}

	public void setLabs(ArrayList<LabModel> labs) {
		this.labs = labs;
	}
	
	public String getTable() {
		if(table == null) {
			table = "<table class='table table-striped'><tr>";
			table += "<th></th>";
			for (String wday : wdays) {
				table += "<th>" + wday + "</th>";
			}
			table += "</tr>";
			
			for(int i = 7; i < 23; i++) {
				table += "<tr><td>" + i + ":00</td>";
				for (String wday : wdays) {
					table += "<td><span id='booking-cell' class='btn' data-booking='" + wday + '-' + i + "'><i class='icon-plus' /></td>";
				}
				table += "</tr>";
			}
			
			table += "</table>";
		}
		return table;
	}
	
	
}
