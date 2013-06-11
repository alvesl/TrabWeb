package jsp.trab3.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name="BOOKING")
public class BookingModel {

	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private int id;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	private UserModel user;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	private LabModel lab;

	@OneToMany(cascade=CascadeType.ALL,
	fetch=FetchType.EAGER)
	@JoinColumn(name="booking_ID")
	private List<DateTimeModel> dateTimes = new ArrayList<DateTimeModel>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public LabModel getLab() {
		return lab;
	}

	public void setLab(LabModel lab) {
		this.lab = lab;
	}
	
	public List<DateTimeModel> getDateTimes() {
		return dateTimes;
	}

	public void setDateTimes(List<DateTimeModel> dateTimes) {
		this.dateTimes = dateTimes;
	}
	
}
