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
package com.fitnessapp.views.basicworkout;

import com.fitnessapp.api.client.BasicWorkoutClient;
import com.fitnessapp.api.client.BodyTypeClient;
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

@Named("basicWorkoutEditView")
@ViewScoped
public class BasicWorkoutEditView implements Serializable {

    private String basicWorkoutName;
    private String basicWorkoutDuration;

    private BasicWorkoutClient basicWorkoutClient = new BasicWorkoutClient();
    private BasicWorkout bw = new BasicWorkout();
    private List<BasicWorkout> basicWorkouts = new ArrayList<BasicWorkout>();

    private List<BodyType> bodyTypeOptions = new ArrayList<BodyType>();
    private BodyTypeClient bodyTypeClient = new BodyTypeClient();
    private BodyType bodyType = new BodyType();

    private List<String> durationOptions = new ArrayList<String>();

    private boolean durationHasChanged = false;
    private boolean bodyTypeHasChanged = false;
    private int duration;

    @PostConstruct
    public void init() {

        basicWorkouts = basicWorkoutClient.findAll(new GenericType<List<BasicWorkout>>() {
        });
        bodyTypeOptions = bodyTypeClient.findAll(new GenericType<List<BodyType>>() {
        });

        durationOptions.add("5 days");
        durationOptions.add("3 days");

    }

    public List<BasicWorkout> getBasicWorkouts() {
        return basicWorkouts;
    }

    public void setBasicWorkouts(List<BasicWorkout> basicWorkouts) {
        this.basicWorkouts = basicWorkouts;
    }

    public String getBasicWorkoutName() {
        return basicWorkoutName;
    }

    public void setBasicWorkoutName(String basicWorkoutName) {
        this.basicWorkoutName = basicWorkoutName;
    }

    public String getBasicWorkoutDuration() {
        return basicWorkoutDuration;
    }

    public void setBasicWorkoutDuration(String basicWorkoutDuration) {
        this.basicWorkoutDuration = basicWorkoutDuration;
    }

    public BasicWorkout getBw() {
        return bw;
    }

    public void setBw(BasicWorkout bw) {
        this.bw = bw;
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

        bw = (BasicWorkout) event.getObject();
        if (durationHasChanged) {
            bw.setDuration(duration);
        }
        if (bodyTypeHasChanged) {
            bw.setBodyTypeId(bodyType);
        }
        basicWorkoutClient.edit(bw, bw.getId().toString());

        FacesMessage msg = new FacesMessage("BasicWorkoutEdited", "");
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

        for (BasicWorkout q : basicWorkouts) {
            if (q.getId().equals(idWorkout)) {
                if (basicWorkoutDuration.equals("5 days")) {
                    q.setDuration(5);
                    duration = 5;
                } else if (basicWorkoutDuration.equals("3 days")) {
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

        for (BasicWorkout q : basicWorkouts) {
            if (q.getId().equals(idWorkout)) {
                q.setBodyTypeId(bodyType);
                bodyTypeHasChanged = true;
                break;
            }
        }
    }

//    public void delete() {
//        try {
//            BasicWorkoutClient bwc = new BasicWorkoutClient();
//            FacesContext facesContext = FacesContext.getCurrentInstance();
//            String idT = (String) facesContext.getExternalContext().getRequestParameterMap().get("idT");
//
//            if (idT != null && !"".equals(idT)) {
//                bwc.remove(idT);
//                basicWorkouts = bwc.findAll(new GenericType<List<BasicWorkout>>() {
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
