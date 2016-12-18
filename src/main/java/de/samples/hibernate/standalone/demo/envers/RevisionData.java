/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.samples.hibernate.standalone.demo.envers;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

/**
 * Additional revision data.
 * 
 * Replace REVINFO table.
 * 
 * @author macuser
 */
@Entity
@RevisionEntity(RevisionDataListener.class)
public class RevisionData extends DefaultRevisionEntity {

 
    @Temporal(TemporalType.TIMESTAMP)
    private Date changeDate;
 
    private String username;
 
    //Getter und Setter

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
