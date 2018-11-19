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

import com.fitnessapp.api.entities.BasicExercise;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Naluem
 */
@Named("basicExercisedtAddRowView")
@ViewScoped
public class BasicExerciseAddRowView implements Serializable {

	private List<BasicExercise> basicExercises;

	/*@ManagedProperty("#{carService}")
    private CarService service;*/
	@PostConstruct
	public void init() {
		//cars1 = service.createCars(15);
		basicExercises=new ArrayList<>();
		BasicExercise basicExercise=new BasicExercise(1, "tocarse los huevos basicamente", 5, 20);
		basicExercise.setBasicWorkoutId(1);
		basicExercise.setMuscularGroupId(1);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);
		basicExercises.add(basicExercise);

	}

	public List<BasicExercise> getBasicExercises() {
		return basicExercises;
	}

	public void setBasicExercises(List<BasicExercise> basicExercises) {
		this.basicExercises = basicExercises;
	}
	

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("BasicExerciseEdited", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onAddNew() {
		FacesMessage msg = new FacesMessage("New BasicExercise added", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
