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

import com.fitnessapp.api.client.BasicExerciseClient;
import com.fitnessapp.api.entities.BasicExercise;
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

/**
 *
 * @author Naluem
 */
@Named("basicExerciseEditView")
@ViewScoped
public class BasicExerciseEditView implements Serializable {

    private List<BasicExercise> basicExercises;
    private BasicExercise bw = new BasicExercise();

    @PostConstruct
    public void init() {
        basicExercises = new ArrayList<BasicExercise>();
        basicExercises = getBasicExercises();
    }

    public List<BasicExercise> getBasicExercises() {
        BasicExerciseClient bec = new BasicExerciseClient();
        List<BasicExercise> basicExtmp = bec.findAll(new GenericType<List<BasicExercise>>() {
        });
        bec.close();
        return basicExtmp;
    }

    public void setBasicExercises(List<BasicExercise> basicExercises) {
        this.basicExercises = basicExercises;
    }

    public void onRowEdit(RowEditEvent event) {
        BasicExerciseClient bec = new BasicExerciseClient();
        bec.edit((BasicExercise) event.getObject(), ((BasicExercise) event.getObject()).getId().toString());
        bec.close();
        FacesMessage msg = new FacesMessage("BasicExerciseEdited", "");
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
    
    public void delete(String id) {
        BasicExerciseClient bec = new BasicExerciseClient();
        bec.remove(id);
        bec.close();
        FacesMessage msg = new FacesMessage("Data Deleted", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
