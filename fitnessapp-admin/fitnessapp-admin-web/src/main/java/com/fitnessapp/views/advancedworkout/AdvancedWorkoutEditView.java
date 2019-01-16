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
package com.fitnessapp.views.advancedworkout;

import com.fitnessapp.api.client.AdvancedWorkoutClient;
import com.fitnessapp.api.client.BasicWorkoutClient;
import com.fitnessapp.api.client.BodyTypeClient;
import com.fitnessapp.api.entities.AdvancedWorkout;
import com.fitnessapp.api.entities.BasicWorkout;
import com.fitnessapp.api.entities.BodyType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.ws.rs.core.GenericType;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

@Named("advancedWorkoutEditView")
@ViewScoped
public class AdvancedWorkoutEditView implements Serializable {

    private String advancedWorkoutName;
    private String advancedWorkoutDuration;

    private AdvancedWorkoutClient advancedWorkoutClient = new AdvancedWorkoutClient();
    private AdvancedWorkout aw = new AdvancedWorkout();
    private List<AdvancedWorkout> advancedWorkouts = new ArrayList<AdvancedWorkout>();

    private List<BodyType> bodyTypeOptions = new ArrayList<BodyType>();
    private BodyTypeClient bodyTypeClient = new BodyTypeClient();
    private BodyType bodyType = new BodyType();

    private List<String> durationOptions = new ArrayList<String>();

    private boolean durationHasChanged = false;
    private boolean bodyTypeHasChanged = false;
    private int duration;

    @PostConstruct
    public void init() {

        advancedWorkouts = advancedWorkoutClient.findAll(new GenericType<List<AdvancedWorkout>>() {
        });
        bodyTypeOptions = bodyTypeClient.findAll(new GenericType<List<BodyType>>() {
        });

        durationOptions.add("5 days");
        durationOptions.add("3 days");

    }

    public String getAdvancedWorkoutName() {
        return advancedWorkoutName;
    }

    public void setAdvancedWorkoutName(String advancedWorkoutName) {
        this.advancedWorkoutName = advancedWorkoutName;
    }

    public String getAdvancedWorkoutDuration() {
        return advancedWorkoutDuration;
    }

    public void setAdvancedWorkoutDuration(String advancedWorkoutDuration) {
        this.advancedWorkoutDuration = advancedWorkoutDuration;
    }

    public AdvancedWorkout getAw() {
        return aw;
    }

    public void setAw(AdvancedWorkout aw) {
        this.aw = aw;
    }

    public List<AdvancedWorkout> getAdvancedWorkouts() {
        return advancedWorkouts;
    }

    public void setAdvancedWorkouts(List<AdvancedWorkout> advancedWorkouts) {
        this.advancedWorkouts = advancedWorkouts;
    }

    public List<BodyType> getBodyTypeOptions() {
        return bodyTypeOptions;
    }

    public void setBodyTypeOptions(List<BodyType> bodyTypeOptions) {
        this.bodyTypeOptions = bodyTypeOptions;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public List<String> getDurationOptions() {
        return durationOptions;
    }

    public void setDurationOptions(List<String> durationOptions) {
        this.durationOptions = durationOptions;
    }

    public boolean isDurationHasChanged() {
        return durationHasChanged;
    }

    public void setDurationHasChanged(boolean durationHasChanged) {
        this.durationHasChanged = durationHasChanged;
    }

    public boolean isBodyTypeHasChanged() {
        return bodyTypeHasChanged;
    }

    public void setBodyTypeHasChanged(boolean bodyTypeHasChanged) {
        this.bodyTypeHasChanged = bodyTypeHasChanged;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void onRowEdit(RowEditEvent event) {

        aw = (AdvancedWorkout) event.getObject();
        if (durationHasChanged) {
            aw.setDuration(duration);
        }
        if (bodyTypeHasChanged) {
            aw.setBodyTypeId(bodyType);
        }
        advancedWorkoutClient.edit(aw, aw.getId().toString());

        FacesMessage msg = new FacesMessage("AdvancedWorkoutEdited", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onAddNew() {
        FacesMessage msg = new FacesMessage("New BasicWorkout added", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void recuperarValorCampDuration(AjaxBehaviorEvent e) {
        //assign new value to localeCode
        FacesContext facesContext = FacesContext.getCurrentInstance();
        int idWorkout = Integer.parseInt(facesContext.getExternalContext().getRequestParameterMap().get("idWorkout"));

        for (AdvancedWorkout q : advancedWorkouts) {
            if (q.getId().equals(idWorkout)) {
                if (advancedWorkoutDuration.equals("5 days")) {
                    q.setDuration(5);
                    duration = 5;
                } else if (advancedWorkoutDuration.equals("3 days")) {
                    q.setDuration(3);
                    duration = 3;
                }
                durationHasChanged = true;
                break;
            }
        }
    }

    public void recuperarValorCampBodyType(AjaxBehaviorEvent e) {
        //assign new value to localeCode
        Integer idBodyType = (Integer) ((UIOutput) e.getSource()).getValue();
        bodyType = bodyTypeClient.find(BodyType.class, idBodyType.toString());

        FacesContext facesContext = FacesContext.getCurrentInstance();
        int idWorkout = Integer.parseInt(facesContext.getExternalContext().getRequestParameterMap().get("idWorkout"));

        for (AdvancedWorkout q : advancedWorkouts) {
            if (q.getId().equals(idWorkout)) {
                q.setBodyTypeId(bodyType);
                bodyTypeHasChanged = true;
                break;
            }
        }
    }

//    public void delete() {
//        try {
//            AdvancedWorkoutClient bwc = new AdvancedWorkoutClient();
//            FacesContext facesContext = FacesContext.getCurrentInstance();
//            String idT = (String) facesContext.getExternalContext().getRequestParameterMap().get("idT");
//
//            if (idT != null && !"".equals(idT)) {
//                bwc.remove(idT);
//                advancedWorkouts = bwc.findAll(new GenericType<List<AdvancedWorkout>>() {
//                });
//            }
//
//            FacesContext.getCurrentInstance().addMessage("llist", new FacesMessage(FacesMessage.SEVERITY_INFO, "Deletion succeed", null));
//        } catch (Exception e) {
//            FacesContext.getCurrentInstance().addMessage(
//                    "llist",
//                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR when deleting", null));
//        }
//
//    }
}
