package yfsc.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="RegistrationTerms")
public class RegistrationTerm extends EntityObject {

	public RegistrationTerm() {
		this.calendarItems = new LinkedList<CalendarItem>();
	}
    
    @Column(name="TermName")
    private String termName;
    
    @Column(name="StartDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    
    @Column(name="EndDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;

    @OneToMany(mappedBy = "registrationTerm")
	private List<CalendarItem> calendarItems;
	
	@OneToMany
	@JoinColumn(name="RegistrationTermID")
	private List<SkatingSession> sessions;

    
    
    private static SimpleDateFormat dateFormat;
    static {
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    }
    
    public String getStartDateString() {
        return dateFormat.format(getStartDate());
    }
    public void setStartDateString(String startDate) {
        try {
            this.setStartDate(dateFormat.parse(startDate.trim()));
        }
        catch (Throwable x) {
            this.setStartDate(null);
        }
    }
    public String getEndDateString() {
        return dateFormat.format(getEndDate());
    }
    public void setEndDateString(String endDate) {
        try {
            this.setEndDate(dateFormat.parse(endDate.trim()));
        }
        catch (Throwable x) {
            this.setEndDate(null);
        }
    }

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<CalendarItem> getCalendarItems() {
		return calendarItems;
	}

	public void setCalendarItems(List<CalendarItem> calendarItems) {
		this.calendarItems = calendarItems;
	}

	public List<SkatingSession> getSessions() {
		return sessions;
	}

	public void setSessions(List<SkatingSession> sessions) {
		this.sessions = sessions;
	}
}