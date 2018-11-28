/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fitnessapp.api.entities;

import java.io.Serializable;

/**
 *
 * @author Naluem
 */
public class BasicWorkout implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer bodyTypeId;

    public BasicWorkout() {
    }

    public BasicWorkout(Integer id) {
        this.id = id;
    }
    
    public BasicWorkout(Integer id, Integer bodyTypeId) {
        this.id = id;
        this.bodyTypeId = bodyTypeId;
    }

    public BasicWorkout(Integer id, Integer bodyTypeId) {
        this.id = id;
        this.bodyTypeId = bodyTypeId;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBodyTypeId() {
        return bodyTypeId;
    }

    public void setBodyTypeId(Integer bodyTypeId) {
        this.bodyTypeId = bodyTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BasicWorkout)) {
            return false;
        }
        BasicWorkout other = (BasicWorkout) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fitnessapp.entities.BasicWorkout[ id=" + id + " ]";
    }

}
