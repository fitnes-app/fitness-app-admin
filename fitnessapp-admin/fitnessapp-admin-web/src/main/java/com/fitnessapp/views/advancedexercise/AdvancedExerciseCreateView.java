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
    private List<Integer> workoutIds;
    private List<Integer> mgroupIds;
    private AdvancedExerciseClient bec = new AdvancedExerciseClient();

    private AdvancedExercise be = new AdvancedExercise();
    private List<AdvancedExercise> btl = new ArrayList<AdvancedExercise>();

    @PostConstruct
    public void init() {
        workoutIds = getWorkoutIds();
        mgroupIds = getMgroupIds();
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

    public AdvancedExerciseClient getAdvancedExerciseClient() {
        return bec;
    }

    public void setAdvancedExerciseClient(AdvancedExerciseClient bec) {
        this.bec = bec;
    }

    public void setMuscularGroupId(Integer muscularGroupId) {
        this.muscularGroupId = muscularGroupId;
    }

    public List<Integer> getWorkoutIds() {
        AdvancedWorkoutClient bClient = new AdvancedWorkoutClient();
        List<AdvancedWorkout> tmpWorkout = bClient.findAll(new GenericType<List<AdvancedWorkout>>() {
        });
        List<Integer> tmpIds = new ArrayList<>();
        for (AdvancedWorkout t : tmpWorkout) {
            tmpIds.add(t.getId());
        }
        return tmpIds;
    }

    public void setWorkoutIds(List<Integer> mgroupIds) {
        this.mgroupIds = mgroupIds;
    }

    public List<Integer> getMgroupIds() {
        MuscularGroupClient mgclient = new MuscularGroupClient();
        List<MuscularGroup> tmpMG = mgclient.findAll(new GenericType<List<MuscularGroup>>() {
        });
        List<Integer> tmpIds = new ArrayList<>();
        for (MuscularGroup t : tmpMG) {
            tmpIds.add(t.getId());
        }
        return tmpIds;
    }

    public void setMgroupIds(List<Integer> mgroupIds) {
        this.mgroupIds = mgroupIds;
    }

    public void save() {
        be.setExerciseName(exerciseName);
        be.setDescription(description);
        be.setExerciseSets(exerciseSets);
        be.setRepetitions(repetitions);
        be.setAdvancedWorkoutId(advancedWorkoutId);
        be.setMuscularGroupId(muscularGroupId);
        if (exerciseName != null && description != null && exerciseSets != 0 && repetitions != 0 && advancedWorkoutId != 0 && muscularGroupId != 0) {

            bec.create(be);

            addMessage("AdvancedExercise Added");
        }
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
