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
import com.fitnessapp.api.client.DailyBasicWorkoutClient;
import com.fitnessapp.api.entities.BasicWorkout;
import com.fitnessapp.api.entities.BodyType;
import com.fitnessapp.api.entities.DailyBasicWorkout;
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
    
    private DailyBasicWorkout dailyBasicWorkout = new DailyBasicWorkout();
    private DailyBasicWorkoutClient dailyBasicWorkoutClient = new DailyBasicWorkoutClient();

    private List<Integer> threeDays;
    private List<Integer> fiveDays;

    private Integer workoutId;
    
    @PostConstruct
    public void init() {
        bwc = new BasicWorkoutClient();
        bodyTypeOptions = bodyTypeClient.findAll(new GenericType<List<BodyType>>() {
        });

        durationOptions.add("5 days");
        durationOptions.add("3 days");
        
        threeDays = new ArrayList<Integer>() {
            {
                add(1);
                add(3);
                add(5);
            }
        };
        fiveDays = new ArrayList<Integer>() {
            {
                add(1);
                add(2);
                add(3);
                add(4);
                add(5);
            }
        };
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

    public DailyBasicWorkout getDailyBasicWorkout() {
        return dailyBasicWorkout;
    }

    public void setDailyBasicWorkout(DailyBasicWorkout dailyBasicWorkout) {
        this.dailyBasicWorkout = dailyBasicWorkout;
    }

    public List<Integer> getThreeDays() {
        return threeDays;
    }

    public void setThreeDays(List<Integer> threeDays) {
        this.threeDays = threeDays;
    }

    public List<Integer> getFiveDays() {
        return fiveDays;
    }

    public void setFiveDays(List<Integer> fiveDays) {
        this.fiveDays = fiveDays;
    }

    public Integer getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Integer workoutId) {
        this.workoutId = workoutId;
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
        
        bwf = bwc.findByName(BasicWorkout.class, basicWorkoutName);
        workoutId = bwf.getId();
        for (int i = 0; i < bwf.getDuration(); i++) {
            dailyBasicWorkout.setBasicWorkoutId(bwf);
            if (bwf.getDuration() == 3) {
                dailyBasicWorkout.setWeek_day(threeDays.get(i));
            } else if (bwf.getDuration() == 5) {
                dailyBasicWorkout.setWeek_day(fiveDays.get(i));
            }
            dailyBasicWorkoutClient.create(dailyBasicWorkout);
        }
        
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
