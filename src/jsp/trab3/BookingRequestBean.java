package jsp.trab3;

public class BookingRequestBean {
	private String userName;
	private String dateTime;
	private String table;
	private String[] wdays;
	
	public BookingRequestBean() {
		wdays = new String[7];
		wdays[0] = "seg";
		wdays[1] = "ter";
		wdays[2] = "qua";
		wdays[3] = "qui";
		wdays[4] = "sex";
		wdays[5] = "sab";
		wdays[6] = "dom";
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
		table = "<table class='table table-hover'><tr>";
		table += "<th></th>";
		for (String wday : wdays) {
			table += "<th>" + wday + "</th>";
		}
		table += "</tr>";
		
		for(int i = 7; i < 23; i++) {
			table += "<tr><td>" + i + ":00</td>";
			for (String wday : wdays) {
				table += "<td>" + "</td>";
			}
			table += "</tr>";
		}
		
		table += "</table>";
		return table;
	}
}
