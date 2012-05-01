package yfsc.entities;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SkatingSessions")
public class SkatingSession extends EntityObject {
	
	@Column(name="Name")
	private String name;
	
	@Column(name="StartDate")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column(name="WeeksDuration")
	private int weeksDuration;
	
	@Column(name="DayOfWeek")
	private String dayOfWeek;
	
	@Column(name="StartTime")
	private String startTime;
	
	@Column(name="EndTime")
	private String endTime;
	
	@Column(name="TotalCost")
	private BigDecimal totalCost;
	
	@Column(name="Description")
	private String description;

	
	
	
	
	
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
	
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getWeeksDuration() {
		return weeksDuration;
	}

	public void setWeeksDuration(int weeksDuration) {
		this.weeksDuration = weeksDuration;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
