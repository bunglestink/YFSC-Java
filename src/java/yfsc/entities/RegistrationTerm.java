package yfsc.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="RegistrationTerms")
public class RegistrationTerm extends EntityObject {
    
    @Column(name="TermName")
    private String termName;
    
    @Column(name="StartDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    
    @Column(name="EndDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;

    
    
    
    
    private static SimpleDateFormat dateFormat;
    static {
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    }
    
    public String getStartDateString() {
        return dateFormat.format(startDate);
    }
    public void setStartDateString(String startDate) {
        try {
            System.out.println(startDate);
            System.out.println(dateFormat.parse(startDate));
            this.startDate = dateFormat.parse(startDate.trim());
        }
        catch (Throwable x) {
            this.startDate = null;
        }
    }
    public String getEndDateString() {
        return dateFormat.format(endDate);
    }
    public void setEndDateString(String endDate) {
        try {
            this.endDate = dateFormat.parse(endDate.trim());
        }
        catch (Throwable x) {
            this.endDate = null;
        }
    }
    

    /**
     * @return the termName
     */
    public String getTermName() {
        return termName;
    }

    /**
     * @param termName the termName to set
     */
    public void setTermName(String termName) {
        this.termName = termName;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}