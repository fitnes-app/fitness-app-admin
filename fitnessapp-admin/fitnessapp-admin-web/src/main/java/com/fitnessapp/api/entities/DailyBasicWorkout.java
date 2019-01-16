/*
 * Copyright (C) 2019 Jordi
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.fitnessapp.api.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Jordi
 */
public class DailyBasicWorkout implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer week_day;
    private BasicWorkout basicWorkoutId;
    private Set<BasicExercise> basicExercises = new HashSet<BasicExercise>(0);

    public DailyBasicWorkout(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWeek_day() {
        return week_day;
    }

    public void setWeek_day(Integer week_day) {
        this.week_day = week_day;
    }

    public BasicWorkout getBasicWorkoutId() {
        return basicWorkoutId;
    }

    public void setBasicWorkoutId(BasicWorkout basicWorkoutId) {
        this.basicWorkoutId = basicWorkoutId;
    }

    public Set<BasicExercise> getBasicExercises() {
        return basicExercises;
    }

    public void setBasicExercises(Set<BasicExercise> basicExercises) {
        this.basicExercises = basicExercises;
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
        if (!(object instanceof DailyBasicWorkout)) {
            return false;
        }
        DailyBasicWorkout other = (DailyBasicWorkout) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fitnessapp.entities.DailyBasicWorkout[ id=" + id + " ]";
    }
    
}
