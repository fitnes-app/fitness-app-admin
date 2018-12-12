/*
 * Copyright (C) 2018 Naluem
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
package com.fitnessapp.views.advancedworkout;

import com.fitnessapp.api.entities.AdvancedWorkout;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.RowEditEvent;

@Named("advancedWorkoutAddRowView")
@ViewScoped
public class AdvancedWorkoutAddRowView implements Serializable {

    private List<AdvancedWorkout> advancedWorkouts;

    @PostConstruct
    public void init() {
        advancedWorkouts = new ArrayList<>();

    }

    public List<AdvancedWorkout> getAdvancedWorkouts() {
        return advancedWorkouts;
    }

    public void setAdvancedWorkouts(List<AdvancedWorkout> advancedWorkouts) {
        this.advancedWorkouts = advancedWorkouts;
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("AdvancedWorkoutEdited", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void delete() {
        FacesMessage msg = new FacesMessage("Data deleted", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onAddNew() {
        FacesMessage msg = new FacesMessage("New AdvancedWorkout added", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
