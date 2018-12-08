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
package com.fitnessapp.views.basicworkout;

import com.fitnessapp.api.client.BasicWorkoutClient;
import com.fitnessapp.api.entities.BasicWorkout;
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

@Named("basicWorkoutEditView")
@ViewScoped
public class BasicWorkoutEditView implements Serializable {

    private List<BasicWorkout> basicWorkouts;
    private BasicWorkout bw = new BasicWorkout();

    @PostConstruct
    public void init() {
        basicWorkouts = new ArrayList<BasicWorkout>();
        basicWorkouts = getBasicWorkouts();
    }

    public List<BasicWorkout> getBasicWorkouts() {
        BasicWorkoutClient bwc = new BasicWorkoutClient();
        List<BasicWorkout> basicWorkoutstmp = bwc.findAll(new GenericType<List<BasicWorkout>>() {
        });
        bwc.close();
        return basicWorkoutstmp;
    }

    public void setBasicWorkouts(List<BasicWorkout> basicWorkouts) {
        this.basicWorkouts = basicWorkouts;
    }

    public void onRowEdit(RowEditEvent event) {
        BasicWorkoutClient bwc = new BasicWorkoutClient();
        bwc.edit((BasicWorkout) event.getObject(), ((BasicWorkout) event.getObject()).getId().toString());
        bwc.close();
        FacesMessage msg = new FacesMessage("BasicWorkoutEdited", "");
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

    public void delete() {
        try {
            BasicWorkoutClient bwc = new BasicWorkoutClient();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String idT = (String) facesContext.getExternalContext().getRequestParameterMap().get("idT");

            if (idT != null && !"".equals(idT)) {
                bwc.remove(idT);
                basicWorkouts = bwc.findAll(new GenericType<List<BasicWorkout>>() {
                });
            }

            FacesContext.getCurrentInstance().addMessage("llist", new FacesMessage(FacesMessage.SEVERITY_INFO, "Deletion succeed", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(
                    "llist",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR when deleting", null));
        }

    }
}
