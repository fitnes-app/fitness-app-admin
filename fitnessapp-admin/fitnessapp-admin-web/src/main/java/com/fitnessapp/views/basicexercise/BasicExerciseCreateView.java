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

/**
 *
 * @author Naluem
 */
@Named(value = "basicExerciseCreateView")
@ViewScoped
public class BasicExerciseCreateView implements Serializable {

    private String exerciseName;
    private String description;
    private int exerciseSets;
    private int repetitions;
    private Integer basicWorkoutId;
    private Integer muscularGroupId;
    private List<Integer> workoutIds;
    private List<Integer> mgroupIds;
    private BasicExerciseClient bec = new BasicExerciseClient();

    private BasicExercise be = new BasicExercise();
    private List<BasicExercise> btl = new ArrayList<BasicExercise>();

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

    public Integer getBasicWorkoutId() {
        return basicWorkoutId;
    }

    public void setBasicWorkoutId(Integer basicWorkoutId) {
        this.basicWorkoutId = basicWorkoutId;
    }

    public Integer getMuscularGroupId() {
        return muscularGroupId;
    }

    public BasicExerciseClient getBasicExerciseClient() {
        return bec;
    }

    public void setBasicExerciseClient(BasicExerciseClient bec) {
        this.bec = bec;
    }

    public void setMuscularGroupId(Integer muscularGroupId) {
        this.muscularGroupId = muscularGroupId;
    }

    public List<Integer> getWorkoutIds() {
        BasicWorkoutClient bClient = new BasicWorkoutClient();
        List<BasicWorkout> tmpWorkout = bClient.findAll(new GenericType<List<BasicWorkout>>() {
        });
        List<Integer> tmpIds = new ArrayList<>();
        for (BasicWorkout t : tmpWorkout) {
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
        be.setBasicWorkoutId(basicWorkoutId);
        be.setMuscularGroupId(muscularGroupId);
        if (exerciseName != null && description != null && exerciseSets != 0 && repetitions != 0 && basicWorkoutId != 0 && muscularGroupId != 0) {

            bec.create(be);

            addMessage("BasicExercise Added");
        }
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
