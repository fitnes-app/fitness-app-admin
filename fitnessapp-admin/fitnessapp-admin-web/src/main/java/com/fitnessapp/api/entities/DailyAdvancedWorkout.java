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
public class DailyAdvancedWorkout implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private Integer week_day;
    private AdvancedWorkout advancedWorkoutId;
    private Set<AdvancedExercise> advancedExercises = new HashSet<AdvancedExercise>(0); 
    
    public DailyAdvancedWorkout(){}

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

    public AdvancedWorkout getAdvancedWorkoutId() {
        return advancedWorkoutId;
    }

    public void setAdvancedWorkoutId(AdvancedWorkout advancedWorkoutId) {
        this.advancedWorkoutId = advancedWorkoutId;
    }

    public Set<AdvancedExercise> getAdvancedExercises() {
        return advancedExercises;
    }

    public void setAdvancedExercises(Set<AdvancedExercise> advancedExercises) {
        this.advancedExercises = advancedExercises;
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
        if (!(object instanceof DailyAdvancedWorkout)) {
            return false;
        }
        DailyAdvancedWorkout other = (DailyAdvancedWorkout) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fitnessapp.entities.DailyAdvancedWorkout[ id=" + id + " ]";
    }
}
