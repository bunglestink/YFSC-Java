package yfsc.entities;

import javax.persistence.*;

@Entity
@Table(name="Coaches")
public class Coach extends EntityObject {
    
    @Column(name="Name")
    private String name;
    
    @Column(name="PrimaryInfo")
    private String primaryInfo;
    
    @Column(name="SecondaryInfo")
    private String secondaryInfo;

    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryInfo() {
        return primaryInfo;
    }

    public void setPrimaryInfo(String primaryInfo) {
        this.primaryInfo = primaryInfo;
    }

    public String getSecondaryInfo() {
        return secondaryInfo;
    }

    public void setSecondaryInfo(String secondaryInfo) {
        this.secondaryInfo = secondaryInfo;
    }
}