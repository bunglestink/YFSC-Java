package yfsc.entities;

import javax.persistence.*;

@Entity
@Table(name="CalendarItems")
public class CalendarItem extends EntityObject {
    
	@Column(name="Days")
    private String days;
	
	@Column(name="SaturdayNotes")
	private String SaturdayNotes;
	
	@Column(name="SundayNotes")
	private String SundayNotes;
	
	@ManyToOne
	@JoinColumn(name="RegistrationTermID")
	private RegistrationTerm registrationTerm;

	
	
	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getSaturdayNotes() {
		return SaturdayNotes;
	}

	public void setSaturdayNotes(String SaturdayNotes) {
		this.SaturdayNotes = SaturdayNotes;
	}

	public String getSundayNotes() {
		return SundayNotes;
	}

	public void setSundayNotes(String SundayNotes) {
		this.SundayNotes = SundayNotes;
	}

	public RegistrationTerm getRegistrationTerm() {
		return registrationTerm;
	}

	public void setRegistrationTerm(RegistrationTerm registrationTerm) {
		this.registrationTerm = registrationTerm;
	}
}