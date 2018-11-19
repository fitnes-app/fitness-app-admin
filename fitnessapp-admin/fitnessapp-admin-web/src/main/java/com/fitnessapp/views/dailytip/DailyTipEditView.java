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

import com.fitnessapp.api.entities.DailyTip;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Naluem
 */
@Named(value = "dailyTipdtEditView")
@ViewScoped
public class DailyTipEditView implements Serializable {

	/*@ManagedProperty("#{carService}")
    private CarService service;*/
	private List<DailyTip> dailyTips;

	@PostConstruct
	public void init() {
		/*cars1 = service.createCars(10);
        cars2 = service.createCars(10);*/
		dailyTips = new ArrayList<>();

		DailyTip dailyTip = new DailyTip(1, "Un tip de mierda cada dia");
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
		dailyTips.add(dailyTip);
	}

	/*public void setService(CarService service) {
        this.service = service;
    }*/
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

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

}
