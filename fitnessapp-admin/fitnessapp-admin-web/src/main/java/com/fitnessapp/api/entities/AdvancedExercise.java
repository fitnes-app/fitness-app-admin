/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fitnessapp.api.entities;

import java.io.Serializable;

/**
 *
 * @author Naluem
 */
public class AdvancedExercise implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String exerciseName;
	private String description;
	private int exerciseSets;
	private int repetitions;
	private Integer advancedWorkoutId;
	private Integer muscularGroupId;

	public AdvancedExercise() {
	}

	public AdvancedExercise(Integer id) {
		this.id = id;
	}

	public AdvancedExercise(Integer id, String exerciseName, int exerciseSets, int repetitions) {
		this.id = id;
		this.exerciseName = exerciseName;
		this.exerciseSets = exerciseSets;
		this.repetitions = repetitions;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof AdvancedExercise)) {
			return false;
		}
		AdvancedExercise other = (AdvancedExercise) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.fitnessapp.entities.AdvancedExercise[ id=" + id + " ]";
	}

}