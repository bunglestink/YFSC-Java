package yfsc.entities;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="RegistrationSkaters")
public class AnnualRegistrationSkater extends EntityObject {
	
	public AnnualRegistrationSkater() {
		sessions = new LinkedList<SkatingSession>();
	}
	
	@NotEmpty
	@Column(name="FirstName")
	private String firstName;
	
	@Column(name="MiddleName")
	private String middleName;

	@NotEmpty
	@Column(name="LastName")
	private String lastName;

	@NotEmpty
	@Size(min=1, max=1)
	@Column(name="Sex")
	private String sex;

	@Column(name="USCitizen")
	private boolean usCitizen;

	@NotNull
	@Column(name="BirthDate")
	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@Column(name="NewRegistrant")
	private boolean newRegistrant;

	@NotEmpty
	@Column(name="Level")
	private String level;

	@ManyToMany
	@JoinColumn(table="SkatingSessionSkaters", name="RegistrationSkaterID")
	private List<SkatingSession> sessions;
	
	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public boolean isUsCitizen() {
		return usCitizen;
	}

	public void setUsCitizen(boolean usCitizen) {
		this.usCitizen = usCitizen;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public boolean isNewRegistrant() {
		return newRegistrant;
	}

	public void setNewRegistrant(boolean newRegistrant) {
		this.newRegistrant = newRegistrant;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public List<SkatingSession> getSessions() {
		return sessions;
	}

	public void setSessions(List<SkatingSession> sessions) {
		this.sessions = sessions;
	}
}
