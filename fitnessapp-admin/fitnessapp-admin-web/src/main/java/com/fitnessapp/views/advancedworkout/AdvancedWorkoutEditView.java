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

import com.fitnessapp.api.client.AdvancedWorkoutClient;
import com.fitnessapp.api.entities.AdvancedWorkout;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.ws.rs.core.GenericType;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

@Named("advancedWorkoutEditView")
@ViewScoped
public class AdvancedWorkoutEditView implements Serializable {

    private List<AdvancedWorkout> advancedWorkouts = new ArrayList<AdvancedWorkout>();
    private AdvancedWorkoutClient bwc = new AdvancedWorkoutClient();
    private AdvancedWorkout bw = new AdvancedWorkout();

    @PostConstruct
    public void init() {
        advancedWorkouts = bwc.findAll(new GenericType<List<AdvancedWorkout>>() {
        });
    }

    public List<AdvancedWorkout> getAdvancedWorkouts() {
        return advancedWorkouts;
    }

    public void setAdvancedWorkouts(List<AdvancedWorkout> advancedWorkouts) {
        this.advancedWorkouts = advancedWorkouts;
    }

    public void onRowEdit(RowEditEvent event) {
        bwc.edit((AdvancedWorkout) event.getObject(), ((AdvancedWorkout) event.getObject()).getId().toString());

        FacesMessage msg = new FacesMessage("AdvancedWorkoutEdited", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
