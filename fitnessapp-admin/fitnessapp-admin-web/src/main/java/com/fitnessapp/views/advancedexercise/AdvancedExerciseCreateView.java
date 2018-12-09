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

/**
 *
 * @author Naluem
 */
@Named(value = "advancedExerciseCreateView")
@ViewScoped
public class AdvancedExerciseCreateView implements Serializable {

    private String exerciseName;
    private String description;
    private int exerciseSets;
    private int repetitions;
    private Integer advancedWorkoutId;
    private Integer muscularGroupId;
    private AdvancedWorkoutClient aClient = new AdvancedWorkoutClient();
    private MuscularGroupClient mgclient = new MuscularGroupClient();
    private List<AdvancedWorkout> workoutIds;
    private List<MuscularGroup> mgroupIds;

    private AdvancedExercise be = new AdvancedExercise();
    private List<AdvancedExercise> btl = new ArrayList<AdvancedExercise>();

    @PostConstruct
    public void init() {
        workoutIds = aClient.findAll(new GenericType<List<AdvancedWorkout>>() {
        });
        mgroupIds = mgclient.findAll(new GenericType<List<MuscularGroup>>() {
        });
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExerciseSets() {
        return exerciseSets;
    }

    public void setExerciseSets(int exerciseSets) {
        this.exerciseSets = exerciseSets;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public Integer getAdvancedWorkoutId() {
        return advancedWorkoutId;
    }

    public void setAdvancedWorkoutId(Integer advancedWorkoutId) {
        this.advancedWorkoutId = advancedWorkoutId;
    }

    public Integer getMuscularGroupId() {
        return muscularGroupId;
    }

    public void setMuscularGroupId(Integer muscularGroupId) {
        this.muscularGroupId = muscularGroupId;
    }

    public List<AdvancedWorkout> getWorkoutIds() {
        return workoutIds;
    }

    public void setWorkoutIds(List<AdvancedWorkout> workoutIds) {
        this.workoutIds = workoutIds;
    }

    public List<MuscularGroup> getMgroupIds() {
        return mgroupIds;
    }

    public void setMgroupIds(List<MuscularGroup> mgroupIds) {
        this.mgroupIds = mgroupIds;
    }

    public void save() {
        AdvancedWorkoutClient bwc = new AdvancedWorkoutClient();
        MuscularGroupClient mgc = new MuscularGroupClient();
        AdvancedWorkout bw = bwc.find(AdvancedWorkout.class, advancedWorkoutId.toString());
        MuscularGroup mg = mgc.find(MuscularGroup.class, muscularGroupId.toString());
        AdvancedExercise be = new AdvancedExercise();
        be.setAdvancedWorkoutId(bw);
        be.setMuscularGroupId(mg);
        be.setExerciseName(exerciseName);
        be.setDescription(description);
        be.setExerciseSets(exerciseSets);
        be.setRepetitions(repetitions);
        AdvancedExerciseClient bec = new AdvancedExerciseClient();
        bec.create(be);
        bwc.close();
        mgc.close();
        addMessage("Data saved");
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
