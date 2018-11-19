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
package com.fitnessapp.views.specifictip;

import com.fitnessapp.views.advancedexercise.*;
import com.fitnessapp.api.entities.SpecificTip;
import com.fitnessapp.api.entities.BasicExercise;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Naluem
 */
@Named(value = "specificTipdtAddRowView")
@ViewScoped
public class SpecificTipAddRowView implements Serializable {

	private List<SpecificTip> specificTips;

	/*@ManagedProperty("#{carService}")
    private CarService service;*/
	@PostConstruct
	public void init() {
		//cars1 = service.createCars(15);
		specificTips = new ArrayList<>();
		SpecificTip specificTip = new SpecificTip(1, "Un tip de mierda cada dia",1);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
		specificTips.add(specificTip);
	}

	public List<SpecificTip> getSpecificTips() {
		return specificTips;
	}

	public void setSpecificTips(List<SpecificTip> specificTips) {
		this.specificTips = specificTips;
	}

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("SpecificTipEdited", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void delete() {
		FacesMessage msg = new FacesMessage("Data deleted", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onAddNew() {
		FacesMessage msg = new FacesMessage("New SpecificTip added", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
