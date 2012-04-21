package yfsc.entities;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="Registration")
public class AnnualRegistration extends EntityObject {

	public AnnualRegistration() {
		skaters = new LinkedList<AnnualRegistrationSkater>();
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
	@Column(name="Street")
	private String street;

	@NotEmpty
	@Column(name="City")
	private String city;

	@NotEmpty
	@Size(min=2, max=2)
	@Column(name="State")
	private String state;

	@NotEmpty
	@Column(name="Zip")
	private String zip;

	@NotEmpty
	@Column(name="HomePhone")
	private String homePhone;
	
	@Column(name="WorkPhone")
	private String workPhone;
	
	@NotEmpty
	@Column(name="Email")
	private String email;

	@Column(name="YaleAffiliation")
	private boolean yaleAffiliation;
	
	@Column(name="NameOfAffiliatedPerson")
	private String nameOfAffiliatedPerson;
	
	@Column(name="YaleAffiliationType")
	private String yaleAffiliationType;
	
	@Column(name="Department")
	private String department;
	
	@Column(name="School")
	private String school;
	
	@Column(name="Year")
	private String year;

	@ManyToOne
	@JoinColumn(name="RegistrationTermID")
	private RegistrationTerm registrationTerm;
	
	@OneToMany
	@JoinColumn(name="RegistrationID")
	private List<AnnualRegistrationSkater> skaters;

	
	
	
	
	
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isYaleAffiliation() {
		return yaleAffiliation;
	}

	public void setYaleAffiliation(boolean yaleAffiliation) {
		this.yaleAffiliation = yaleAffiliation;
	}

	public String getNameOfAffiliatedPerson() {
		return nameOfAffiliatedPerson;
	}

	public void setNameOfAffiliatedPerson(String nameOfAffiliatedPerson) {
		this.nameOfAffiliatedPerson = nameOfAffiliatedPerson;
	}

	public String getYaleAffiliationType() {
		return yaleAffiliationType;
	}

	public void setYaleAffiliationType(String yaleAffiliationType) {
		this.yaleAffiliationType = yaleAffiliationType;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public RegistrationTerm getRegistrationTerm() {
		return registrationTerm;
	}

	public void setRegistrationTerm(RegistrationTerm registrationTerm) {
		this.registrationTerm = registrationTerm;
	}

	public List<AnnualRegistrationSkater> getSkaters() {
		return skaters;
	}

	public void setSkaters(List<AnnualRegistrationSkater> skaters) {
		this.skaters = skaters;
	}
}