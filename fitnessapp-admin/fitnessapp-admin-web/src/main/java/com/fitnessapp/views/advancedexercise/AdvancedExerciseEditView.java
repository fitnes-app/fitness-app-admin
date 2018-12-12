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
package com.fitnessapp.views.advancedexercise;

import com.fitnessapp.api.client.AdvancedExerciseClient;
import com.fitnessapp.api.client.AdvancedWorkoutClient;
import com.fitnessapp.api.client.MuscularGroupClient;
import com.fitnessapp.api.entities.AdvancedExercise;
import com.fitnessapp.api.entities.AdvancedWorkout;
import com.fitnessapp.api.entities.MuscularGroup;
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

/**
 *
 * @author Naluem
 */
@Named("advancedExerciseEditView")
@ViewScoped
public class AdvancedExerciseEditView implements Serializable {

    private List<AdvancedExercise> advancedExercises;
    private AdvancedWorkout aw = new AdvancedWorkout();
    private List<AdvancedWorkout> advancedWorkouts;
    private MuscularGroup mg = new MuscularGroup();
    private List<MuscularGroup> muscularGroups;

    @PostConstruct
    public void init() {
        advancedExercises = new ArrayList<AdvancedExercise>();
        advancedExercises = getAdvancedExercises();
        advancedWorkouts = new ArrayList<AdvancedWorkout>();
        advancedWorkouts = getAdvancedWorkouts();
        muscularGroups = new ArrayList<MuscularGroup>();
        muscularGroups = getMuscularGroups();
    }

    public AdvancedWorkout getAw() {
        return aw;
    }

    public void setAw(AdvancedWorkout aw) {
        this.aw = aw;
    }

    public List<AdvancedWorkout> getAdvancedWorkouts() {
        AdvancedWorkoutClient client = new AdvancedWorkoutClient();
        List<AdvancedWorkout> tmpWorkouts = client.findAll(new GenericType<List<AdvancedWorkout>>() {
        });
        client.close();
        return tmpWorkouts;
    }

    public void setAdvancedWorkouts(List<AdvancedWorkout> advancedWorkouts) {
        this.advancedWorkouts = advancedWorkouts;
    }

    public MuscularGroup getMg() {
        return mg;
    }

    public void setMg(MuscularGroup mg) {
        this.mg = mg;
    }

    public List<MuscularGroup> getMuscularGroups() {
        MuscularGroupClient client = new MuscularGroupClient();
        List<MuscularGroup> tmpGroups = client.findAll(new GenericType<List<MuscularGroup>>() {
        });
        client.close();
        return tmpGroups;
    }

    public void setMuscularGroups(List<MuscularGroup> muscularGroups) {
        this.muscularGroups = muscularGroups;
    }

    public List<AdvancedExercise> getAdvancedExercises() {
        AdvancedExerciseClient aec = new AdvancedExerciseClient();
        List<AdvancedExercise> advancedExtmp = aec.findAll(new GenericType<List<AdvancedExercise>>() {
        });
        aec.close();
        return advancedExtmp;
    }

    public void setAdvancedExercises(List<AdvancedExercise> advancedExercises) {
        this.advancedExercises = advancedExercises;
    }

    public void onRowEdit(RowEditEvent event) {
        AdvancedExercise aeT = (AdvancedExercise) event.getObject();
        aeT.setAdvancedWorkoutId(aw);
        aeT.setMuscularGroupId(mg);
        AdvancedExerciseClient aec = new AdvancedExerciseClient();
        aec.edit(aeT, aeT.getId().toString());
        aec.close();

        FacesMessage msg = new FacesMessage("AdvancedExercise Edited", "");
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
            AdvancedExerciseClient aec = new AdvancedExerciseClient();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String idT = (String) facesContext.getExternalContext().getRequestParameterMap().get("idT");

            if (idT != null && !"".equals(idT)) {
                aec.remove(idT);
                advancedExercises = aec.findAll(new GenericType<List<AdvancedExercise>>() {
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
