/*
 * Copyright (C) 2018 Jordi
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

import com.fitnessapp.api.client.MuscularGroupClient;
import com.fitnessapp.api.entities.MuscularGroup;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.core.GenericType;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Jordi
 */
@Named(value = "muscularGroupEditView")
@ViewScoped
public class MuscularGroupEditView implements Serializable {

    private MuscularGroupClient muscularGroupClient = new MuscularGroupClient();

    private MuscularGroup muscularGroup = new MuscularGroup();
    private List<MuscularGroup> muscularGroups = new ArrayList<MuscularGroup>();

    @PostConstruct
    public void init() {

        muscularGroups = muscularGroupClient.findAll(new GenericType<List<MuscularGroup>>() {
        });
    }

    public List<MuscularGroup> getMuscularGroups() {
        return muscularGroups;
    }

    public void setMuscularGroups(List<MuscularGroup> muscularGroups) {
        this.muscularGroups = muscularGroups;
    }

    public void onRowEdit(RowEditEvent event) {
        muscularGroupClient.edit((MuscularGroup) event.getObject(), ((MuscularGroup) event.getObject()).getId().toString());

        FacesMessage msg = new FacesMessage("MuscularGroupEdited", "");
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
