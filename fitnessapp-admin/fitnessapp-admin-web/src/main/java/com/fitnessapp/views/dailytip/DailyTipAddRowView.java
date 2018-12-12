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
package com.fitnessapp.views.dailytip;

import com.fitnessapp.views.specifictip.*;
import com.fitnessapp.views.advancedexercise.*;
import com.fitnessapp.api.entities.DailyTip;
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
@Named(value = "dailyTipdtAddRowView")
@ViewScoped
public class DailyTipAddRowView implements Serializable {

	private List<DailyTip> dailyTips;

	/*@ManagedProperty("#{carService}")
    private CarService service;*/
	@PostConstruct
	public void init() {
		
	}

	public List<DailyTip> getDailyTips() {
		return dailyTips;
	}

	public void setDailyTips(List<DailyTip> dailyTips) {
		this.dailyTips = dailyTips;
	}

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("DailyTipEdited", "");
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
		FacesMessage msg = new FacesMessage("New DailyTip added", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
