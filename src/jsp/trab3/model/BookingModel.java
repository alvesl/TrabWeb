package jsp.trab3.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="BOOKING")
public class BookingModel {

	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private int id;
	
	@OneToOne(cascade=CascadeType.ALL)
	private UserModel user;
	
	@OneToOne(cascade=CascadeType.ALL)
	private LabModel lab;

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
	
	
}
