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
package com.fitnessapp.views.bodytype;
import com.fitnessapp.api.entities.BodyType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.RowEditEvent;

@Named("bodyTypeAddRowView")
@ViewScoped
public class BodyTypeAddRowView implements Serializable {

	private List<BodyType> bodyTypes;

	@PostConstruct
	public void init() {
		bodyTypes=new ArrayList<>();
		BodyType bt1 = new BodyType(1);
                BodyType bt2 = new BodyType(2);
                BodyType bt3 = new BodyType(3);
		bodyTypes.add(bt1);
                bodyTypes.add(bt2);
                bodyTypes.add(bt3);

	}

	public List<BodyType> getBodyTypes() {
		return bodyTypes;
	}

	public void setBodyTypes(List<BodyType> bodyTypes) {
		this.bodyTypes = bodyTypes;
	}
	

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("BodyTypeEdited", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onAddNew() {
		FacesMessage msg = new FacesMessage("New BodyType added", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
