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
package com.fitnessapp.views.basicexercise;

import com.fitnessapp.api.client.BasicExerciseClient;
import com.fitnessapp.api.client.BasicWorkoutClient;
import com.fitnessapp.api.client.MuscularGroupClient;
import com.fitnessapp.api.entities.BasicExercise;
import com.fitnessapp.api.entities.BasicWorkout;
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
@Named("basicExerciseEditView")
@ViewScoped
public class BasicExerciseEditView implements Serializable {

    private List<BasicExercise> basicExercises;
    private BasicWorkout bw = new BasicWorkout();
    private List<BasicWorkout> basicWorkouts;
    private MuscularGroup mg = new MuscularGroup();
    private List<MuscularGroup> muscularGroups;

    @PostConstruct
    public void init() {
        basicExercises = new ArrayList<BasicExercise>();
        basicExercises = getBasicExercises();
        basicWorkouts = new ArrayList<BasicWorkout>();
        basicWorkouts = getBasicWorkouts();
        muscularGroups = new ArrayList<MuscularGroup>();
        muscularGroups = getMuscularGroups();
    }

    public BasicWorkout getBw() {
        return bw;
    }

    public void setBw(BasicWorkout bw) {
        this.bw = bw;
    }

    public List<BasicWorkout> getBasicWorkouts() {
        BasicWorkoutClient client = new BasicWorkoutClient();
        List<BasicWorkout> tmpWorkouts = client.findAll(new GenericType<List<BasicWorkout>>() {
        });
        client.close();
        return tmpWorkouts;
    }

    public void setBasicWorkouts(List<BasicWorkout> basicWorkouts) {
        this.basicWorkouts = basicWorkouts;
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

    public List<BasicExercise> getBasicExercises() {
        BasicExerciseClient bec = new BasicExerciseClient();
        List<BasicExercise> basicExtmp = bec.findAll(new GenericType<List<BasicExercise>>() {
        });
        bec.close();
        return basicExtmp;
    }

    public void setBasicExercises(List<BasicExercise> basicExercises) {
        this.basicExercises = basicExercises;
    }

    public void onRowEdit(RowEditEvent event) {
        BasicExercise beT = (BasicExercise) event.getObject();
        beT.setBasicWorkoutId(bw);
        beT.setMuscularGroupId(mg);
        BasicExerciseClient bec = new BasicExerciseClient();
        bec.edit(beT, beT.getId().toString());
        bec.close();

        FacesMessage msg = new FacesMessage("BasicExercise Edited", "");
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
            BasicExerciseClient bec = new BasicExerciseClient();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String idT = (String) facesContext.getExternalContext().getRequestParameterMap().get("idT");

            if (idT != null && !"".equals(idT)) {
                bec.remove(idT);
                basicExercises = bec.findAll(new GenericType<List<BasicExercise>>() {
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
