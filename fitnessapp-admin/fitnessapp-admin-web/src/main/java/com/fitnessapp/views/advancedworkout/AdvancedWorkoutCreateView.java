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
import com.fitnessapp.api.client.DailyAdvancedWorkoutClient;
import com.fitnessapp.api.client.AdvancedWorkoutClient;
import com.fitnessapp.api.client.BodyTypeClient;
import com.fitnessapp.api.entities.AdvancedWorkout;
import com.fitnessapp.api.entities.BasicWorkout;
import com.fitnessapp.api.entities.BodyType;
import com.fitnessapp.api.entities.DailyAdvancedWorkout;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.ws.rs.core.GenericType;
import static javax.ws.rs.core.Response.temporaryRedirect;

@Named(value = "advancedWorkoutCreateView")
@ViewScoped
public class AdvancedWorkoutCreateView implements Serializable {

    private String advancedWorkoutName;
    private String advancedWorkoutDuration;

    private AdvancedWorkoutClient bwc;
    private AdvancedWorkout bw = new AdvancedWorkout();
    private List<AdvancedWorkout> bwl = new ArrayList<AdvancedWorkout>();

    private List<BodyType> bodyTypeOptions = new ArrayList<BodyType>();
    private BodyTypeClient bodyTypeClient = new BodyTypeClient();
    private BodyType bodyType = new BodyType();

    private List<String> durationOptions = new ArrayList<String>();
    
    private DailyAdvancedWorkout dailyAdvancedWorkout = new DailyAdvancedWorkout();
    private DailyAdvancedWorkoutClient dailyAdvancedWorkoutClient = new DailyAdvancedWorkoutClient();

    private List<Integer> threeDays;
    private List<Integer> fiveDays;
    
    private Integer workoutId;
    
    @PostConstruct
    public void init() {
        bwc = new AdvancedWorkoutClient();
        bodyTypeOptions = bodyTypeClient.findAll(new GenericType<List<BodyType>>() {
        });

        durationOptions.add("5 days");
        durationOptions.add("3 days");
        
        threeDays = new ArrayList<Integer>() {{
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

    public AdvancedWorkout getBw() {
        return bw;
    }

    public void setBw(AdvancedWorkout bw) {
        this.bw = bw;
    }

    public List<AdvancedWorkout> getBwl() {
        return bwl;
    }

    public void setBwl(List<AdvancedWorkout> bwl) {
        this.bwl = bwl;
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

    public DailyAdvancedWorkout getDailyAdvancedWorkout() {
        return dailyAdvancedWorkout;
    }

    public void setDailyAdvancedWorkout(DailyAdvancedWorkout dailyAdvancedWorkout) {
        this.dailyAdvancedWorkout = dailyAdvancedWorkout;
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
    
    public void save() throws IOException {
        AdvancedWorkout bwf = new AdvancedWorkout();
        if (advancedWorkoutDuration.equals("5 days")) {
            bwf.setDuration(5);
        } else if (advancedWorkoutDuration.equals("3 days")) {
            bwf.setDuration(3);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Duration is mandatory", null));
        }
        bwf.setName(advancedWorkoutName);
        bwf.setBodyTypeId(bodyType);
        bwc.create(bwf);
        
        bwf = bwc.findByName(AdvancedWorkout.class, advancedWorkoutName);
        workoutId = bwf.getId();
        for(int i=0; i<bwf.getDuration();i++){
            dailyAdvancedWorkout.setAdvancedWorkoutId(bwf);
            if(bwf.getDuration() == 3){
                dailyAdvancedWorkout.setWeek_day(threeDays.get(i));
            }else if(bwf.getDuration() == 5){
                dailyAdvancedWorkout.setWeek_day(fiveDays.get(i));
            }
            dailyAdvancedWorkoutClient.create(dailyAdvancedWorkout);
        }
        addMessage("Data saved");
        //FacesContext.getCurrentInstance().getExternalContext().redirect("DailyAdvancedWorkout/create.xhtml");
        //return "DailyAdvancedWorkout/create.xhtml?faces-redirect=true&includeViewParams=true";
    }

    public AdvancedWorkoutClient getAdvancedWorkoutClient() {
        return bwc;
    }

    public void setAdvancedWorkoutClient(AdvancedWorkoutClient bwc) {
        this.bwc = bwc;
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
