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
public class AdvancedWorkout implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private BodyType bodyTypeId;
    private String name;
    private Integer duration;

    public AdvancedWorkout() {
    }

    public AdvancedWorkout(Integer id) {
        this.id = id;
    }

    public AdvancedWorkout(BodyType bodyTypeId, String name, Integer duration) {
        this.bodyTypeId = bodyTypeId;
        this.name = name;
        this.duration = duration;
    }
    
    public BodyType getBodyTypeId() {
        return bodyTypeId;
    }

    public void setBodyTypeId(BodyType bodyTypeId) {
        this.bodyTypeId = bodyTypeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
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
        if (!(object instanceof AdvancedWorkout)) {
            return false;
        }
        AdvancedWorkout other = (AdvancedWorkout) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fitnessapp.entities.AdvancedWorkout[ id=" + id + " ]";
    }

}
