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

	@Column(name = "RESPONSAVEL")
	private String responsavel;
	
	@Column(name = "MOTIVO")
	private String motivo;
	
	@Column(name = "PROJETO")
	private String projeto;
	
	@Column(name = "DESCRICAO")
	private String descricao;
	
	@Column(name = "ISHOMOLOG")
	private boolean isHomolog;

	@Column(name = "ISDEFERED")
	private boolean isDefered;
	
	@Column(name = "DEFEREDOBS")
	private String deferedObs;	
	
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

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getProjeto() {
		return projeto;
	}

	public void setProjeto(String projeto) {
		this.projeto = projeto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isHomolog() {
		return isHomolog;
	}

	public void setHomolog(boolean isHomolog) {
		this.isHomolog = isHomolog;
	}

	public boolean isDefered() {
		return isDefered;
	}

	public void setDefered(boolean isDefered) {
		this.isDefered = isDefered;
	}

	public String getDeferedObs() {
		return deferedObs;
	}

	public void setDeferedObs(String deferedObs) {
		this.deferedObs = deferedObs;
	}
	
}
