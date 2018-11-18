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
package com.fitnessapp.views;

import com.fitnessapp.api.entities.AdvancedExercise;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Naluem
 */
@Named(value = "advancedExerciseCreateView")
@ViewScoped
public class AdvancedExerciseCreateView implements Serializable{

	private String exerciseName;
	private String description;
	private int exerciseSets;
	private int repetitions;
	private Integer advancedWorkoutId;
	private Integer muscularGroupId;

	@PostConstruct
	public void init() {
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


	public void save() {
		addMessage("Data saved");
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
