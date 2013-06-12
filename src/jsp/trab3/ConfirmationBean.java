package jsp.trab3;

import jsp.trab3.model.BookingModel;

public class ConfirmationBean {

	private BookingModel bookingModel;
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BookingModel getBookingModel() {
		return bookingModel;
	}

	public void setBookingModel(BookingModel bookingModel) {
		this.bookingModel = bookingModel;
	}
	
}
