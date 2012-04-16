package yfsc.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="Announcements")
public class Announcement extends EntityObject {
    
	@Column(name="AnnouncementDate")
	@Temporal(javax.persistence.TemporalType.DATE)
    private Date announcementDate;
	
	@Column(name="Title")
	private String Title;
	
	@Column(name="Body")
	private String Body;
	
	
	
	
	
	
	private static SimpleDateFormat dateFormat;
    static {
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    }
    
    public String getAnnouncementDateString() {
        return dateFormat.format(announcementDate);
    }
    public void setAnnouncementDateString(String announcementDate) {
        try {
            this.announcementDate = dateFormat.parse(announcementDate.trim());
        }
        catch (Throwable x) {
            this.announcementDate = null;
        }
    }
	
	
	
	
	
	

	public Date getAnnouncementDate() {
		return announcementDate;
	}

	public void setAnnouncementDate(Date announcementDate) {
		this.announcementDate = announcementDate;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String Title) {
		this.Title = Title;
	}

	public String getBody() {
		return Body;
	}

	public void setBody(String Body) {
		this.Body = Body;
	}
}