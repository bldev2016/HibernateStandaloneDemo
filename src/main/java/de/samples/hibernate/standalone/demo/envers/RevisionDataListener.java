/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.samples.hibernate.standalone.demo.envers;

import java.util.Date;
import org.hibernate.envers.RevisionListener;

/**
 * 
 * @author macuser
 */
public class RevisionDataListener implements RevisionListener {
 
    @Override
    public void newRevision(Object o) {
        RevisionData revData = (RevisionData) o;
        revData.setChangeDate(new Date());
        revData.setUsername(getUsername());
    }
 
    private String getUsername() {
        return "Binh Ly";
    }
 
}