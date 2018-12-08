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
    //private BasicWorkoutClient bClient = new BasicWorkoutClient();
    //private MuscularGroupClient mgclient = new MuscularGroupClient();
    private List<BasicWorkout> workoutIds;
    private List<MuscularGroup> mgroupIds;

    private BasicExercise be = new BasicExercise();
    private List<BasicExercise> btl = new ArrayList<BasicExercise>();

    @PostConstruct
    public void init() {
        BasicWorkoutClient bClient = new BasicWorkoutClient();
        MuscularGroupClient mgclient = new MuscularGroupClient();
        workoutIds = bClient.findAll(new GenericType<List<BasicWorkout>>() {
        });
        mgroupIds = mgclient.findAll(new GenericType<List<MuscularGroup>>() {
        });
        bClient.close();
        mgclient.close();
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

    public void setMuscularGroupId(Integer muscularGroupId) {
        this.muscularGroupId = muscularGroupId;
    }

    public List<BasicWorkout> getWorkoutIds() {
        return workoutIds;
    }

    public void setWorkoutIds(List<BasicWorkout> workoutIds) {
        this.workoutIds = workoutIds;
    }

    public List<MuscularGroup> getMgroupIds() {
        return mgroupIds;
    }

    public void setMgroupIds(List<MuscularGroup> mgroupIds) {
        this.mgroupIds = mgroupIds;
    }

    public void save() {
        BasicWorkoutClient bwc = new BasicWorkoutClient();
        MuscularGroupClient mgc = new MuscularGroupClient();
        BasicWorkout bw = bwc.find(BasicWorkout.class, basicWorkoutId.toString());
        MuscularGroup mg = mgc.find(MuscularGroup.class, muscularGroupId.toString());
        BasicExercise be = new BasicExercise();
        be.setBasicWorkoutId(bw);
        be.setMuscularGroupId(mg);
        be.setExerciseName(exerciseName);
        be.setDescription(description);
        be.setExerciseSets(exerciseSets);
        be.setRepetitions(repetitions);
        BasicExerciseClient bec = new BasicExerciseClient();
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
