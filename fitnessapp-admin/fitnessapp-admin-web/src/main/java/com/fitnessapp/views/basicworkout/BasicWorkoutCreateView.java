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
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.ws.rs.core.GenericType;

@Named(value = "basicWorkoutCreateView")
@ViewScoped
public class BasicWorkoutCreateView implements Serializable {

    private String basicWorkoutName;
    private String basicWorkoutDuration;

    private BasicWorkoutClient bwc;
    private BasicWorkout bw = new BasicWorkout();
    private List<BasicWorkout> bwl = new ArrayList<BasicWorkout>();

    private List<BodyType> bodyTypeOptions = new ArrayList<BodyType>();
    private BodyTypeClient bodyTypeClient = new BodyTypeClient();
    private BodyType bodyType = new BodyType();
    
    private List<String> durationOptions = new ArrayList<String>();
    
    @PostConstruct
    public void init() {
        bwc = new BasicWorkoutClient();
        bodyTypeOptions = bodyTypeClient.findAll(new GenericType<List<BodyType>>() {
        });

        durationOptions.add("5 days");
        durationOptions.add("3 days");
    }

    public List<String> getDurationOptions() {
        return durationOptions;
    }

    public void setDurationOptions(List<String> durationOptions) {
        this.durationOptions = durationOptions;
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

    public List<BasicWorkout> getBwl() {
        return bwl;
    }

    public void setBwl(List<BasicWorkout> bwl) {
        this.bwl = bwl;
    }

    public void save() {

        BasicWorkout bwf = new BasicWorkout();
        if(basicWorkoutDuration.equals("5 days")){
            bwf.setDuration(5);
        }else if(basicWorkoutDuration.equals("3 days")){
            bwf.setDuration(3);
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Duration is mandatory", null));
        }
        bwf.setName(basicWorkoutName);
        bwf.setBodyTypeId(bodyType);
        bwc.create(bwf);
        addMessage("Data saved");
    }

    public BasicWorkoutClient getBasicWorkoutClient() {
        return bwc;
    }

    public void setBasicWorkoutClient(BasicWorkoutClient bwc) {
        this.bwc = bwc;
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
