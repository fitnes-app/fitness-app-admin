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
    private Float kcal;

    private List<MuscularGroup> muscularGroupOptions = new ArrayList<MuscularGroup>();
    private MuscularGroupClient muscularGroupClient = new MuscularGroupClient();
    private MuscularGroup muscularGroup = new MuscularGroup();

    private AdvancedExercise ae = new AdvancedExercise();
    private AdvancedExerciseClient advancedExerciseClient = new AdvancedExerciseClient();

    @PostConstruct
    public void init() {
        muscularGroupOptions = muscularGroupClient.findAll(new GenericType<List<MuscularGroup>>() {
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

    public Float getKcal() {
        return kcal;
    }

    public void setKcal(Float kcal) {
        this.kcal = kcal;
    }

    public List<MuscularGroup> getMuscularGroupOptions() {
        return muscularGroupOptions;
    }

    public void setMuscularGroupOptions(List<MuscularGroup> muscularGroupOptions) {
        this.muscularGroupOptions = muscularGroupOptions;
    }

    public MuscularGroup getMuscularGroup() {
        return muscularGroup;
    }

    public void setMuscularGroup(MuscularGroup muscularGroup) {
        this.muscularGroup = muscularGroup;
    }

    public AdvancedExercise getAe() {
        return ae;
    }

    public void setAe(AdvancedExercise ae) {
        this.ae = ae;
    }

    public void save() {
        ae.setExerciseName(exerciseName);
        ae.setDescription(description);
        ae.setExerciseSets(exerciseSets);
        ae.setRepetitions(repetitions);
        ae.setKcal(kcal);
        ae.setMuscularGroupId(muscularGroup);
        advancedExerciseClient.create(ae);
        addMessage("Data saved");
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
