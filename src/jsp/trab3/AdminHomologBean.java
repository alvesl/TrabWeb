package jsp.trab3;

public class AdminHomologBean {
	private String userName;
	private String dateTime;
	private String table;
	private String[] wdays;
	
	public AdminHomologBean() {
		wdays = new String[7];
		wdays[1] = "seg";
		wdays[2] = "ter";
		wdays[3] = "qua";
		wdays[4] = "qui";
		wdays[5] = "sex";
		wdays[6] = "sab";
		wdays[0] = "dom";
		
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
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getTable() {
		if (table == null) {
			table = "<table class='table table-striped'><tr>";
			table += "<th></th>";
			for (String wday : wdays) {
				table += "<th>" + wday + "</th>";
			}
			table += "</tr>";
			
			for(int i = 7; i < 23; i++) {
				table += "<tr><td>" + i + ":00</td>";
				for (String wday : wdays) {
					table += "<td><span id='booking-cell' data-booking='" + wday + '-' + i + "'></span></td>";
				}
				table += "</tr>";
			}
			
			table += "</table>";
		} 

		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
}
