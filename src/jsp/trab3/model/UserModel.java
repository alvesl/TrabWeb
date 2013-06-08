package jsp.trab3.model;

import javax.persistence.*;



@Entity
@Table(name = "USER")
public class UserModel {
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "LOGIN")
	private String login;
	
	@Column(name = "PASSWORD")
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
	
	
}
