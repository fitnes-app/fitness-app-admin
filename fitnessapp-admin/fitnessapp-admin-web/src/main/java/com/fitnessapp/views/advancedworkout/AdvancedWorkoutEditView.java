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
import com.fitnessapp.api.client.BodyTypeClient;
import com.fitnessapp.api.entities.AdvancedWorkout;
import com.fitnessapp.api.entities.BodyType;
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

    private List<AdvancedWorkout> advancedWorkouts;
    private List<BodyType> bodyTypes;
    private AdvancedWorkout aw = new AdvancedWorkout();
    private BodyType bt;

    @PostConstruct
    public void init() {
        advancedWorkouts = new ArrayList<AdvancedWorkout>();
        advancedWorkouts = getAdvancedWorkouts();
        bt = new BodyType();
        bodyTypes = getBodyTypes();
    }

    public List<AdvancedWorkout> getAdvancedWorkouts() {
        AdvancedWorkoutClient bwc = new AdvancedWorkoutClient();
        List<AdvancedWorkout> advancedWorkoutstmp = bwc.findAll(new GenericType<List<AdvancedWorkout>>() {
        });
        bwc.close();
        return advancedWorkoutstmp;
    }

    public void setAdvancedWorkouts(List<AdvancedWorkout> advancedWorkouts) {
        this.advancedWorkouts = advancedWorkouts;
    }

    public List<BodyType> getBodyTypes() {
        BodyTypeClient client = new BodyTypeClient();
        List<BodyType> tmpTypes = client.findAll(new GenericType<List<BodyType>>() {
        });
        client.close();
        return tmpTypes;
    }

    public void setBodyTypes(List<BodyType> bodyTypes) {
        this.bodyTypes = bodyTypes;
    }

    public BodyType getBt() {
        return bt;
    }

    public void setBt(BodyType bt) {
        this.bt = bt;
    }

    public void onRowEdit(RowEditEvent event) {        
        AdvancedWorkout aw = (AdvancedWorkout)event.getObject();;
        aw.setBodyTypeId(bt);
        AdvancedWorkoutClient bwc = new AdvancedWorkoutClient();
        bwc.edit(aw, aw.getId().toString());
        bwc.close();
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

    public void delete() {
        try {
            AdvancedWorkoutClient awc = new AdvancedWorkoutClient();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String idT = (String) facesContext.getExternalContext().getRequestParameterMap().get("idT");

            if (idT != null && !"".equals(idT)) {
                awc.remove(idT);
                advancedWorkouts = awc.findAll(new GenericType<List<AdvancedWorkout>>() {
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
