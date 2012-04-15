package yfsc.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="RegistrationTerms")
public class RegistrationTerm implements Serializable {
    @Id
    @Column(name="ID")
    private int ID;
    
    @Column(name="TermName")
    private String TermName;
    
    @Column(name="StartDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date StartDate;
    
    @Column(name="EndDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date EndDate;

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public String getTermName() {
        return TermName;
    }
    public void setTermName(String TermName) {
        this.TermName = TermName;
    }
    public Date getStartDate() {
        return StartDate;
    }
    public void setStartDate(Date StartDate) {
        this.StartDate = StartDate;
    }
    public Date getEndDate() {
        return EndDate;
    }
    public void setEndDate(Date EndDate) {
        this.EndDate = EndDate;
    }
}