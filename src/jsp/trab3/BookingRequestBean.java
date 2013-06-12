package jsp.trab3;

public class BookingRequestBean {
	private String userName;
	private String dateTime;
	private String table;
	private String[] wdays;
	
	public BookingRequestBean() {
		wdays = new String[7];
		wdays[0] = "segunda";
		wdays[1] = "terça";
		wdays[2] = "quarta";
		wdays[3] = "quinta";
		wdays[4] = "sexta";
		wdays[5] = "sábado";
		wdays[6] = "domingo";
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
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getTable() {
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
		return table;
	}
}
