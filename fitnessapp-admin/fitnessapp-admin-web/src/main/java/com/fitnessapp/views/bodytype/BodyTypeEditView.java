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

import com.fitnessapp.api.client.BodyTypeClient;
import com.fitnessapp.api.entities.BasicExercise;
import com.fitnessapp.api.entities.BodyType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.ws.rs.core.GenericType;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

@Named("bodyTypeEditView")
@ViewScoped
public class BodyTypeEditView implements Serializable {

    private List<BodyType> bodyTypes = new ArrayList<BodyType>();
    private BodyTypeClient btc = new BodyTypeClient();
    private BodyType bt = new BodyType();

    @PostConstruct
    public void init() {
        bodyTypes = btc.findAll(new GenericType<List<BodyType>>() {
        });
    }

    public List<BodyType> getBodyTypes() {
        return bodyTypes;
    }

    public void setBodyTypes(List<BodyType> bodyTypes) {
        this.bodyTypes = bodyTypes;
    }

    public void onRowEdit(RowEditEvent event) {
        btc.edit((BodyType) event.getObject(), ((BodyType) event.getObject()).getId().toString());

        FacesMessage msg = new FacesMessage("BodyTypeEdited", "");
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
