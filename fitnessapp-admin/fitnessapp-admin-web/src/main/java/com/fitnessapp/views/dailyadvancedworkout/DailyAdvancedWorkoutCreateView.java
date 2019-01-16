/*
 * Copyright (C) 2019 Jordi
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
package com.fitnessapp.views.dailyadvancedworkout;

import com.fitnessapp.api.client.AdvancedExerciseClient;
import com.fitnessapp.api.client.AdvancedWorkoutClient;
import com.fitnessapp.api.client.DailyAdvancedWorkoutClient;
import com.fitnessapp.api.client.MuscularGroupClient;
import com.fitnessapp.api.entities.AdvancedExercise;
import com.fitnessapp.api.entities.AdvancedWorkout;
import com.fitnessapp.api.entities.BodyType;
import com.fitnessapp.api.entities.DailyAdvancedWorkout;
import com.fitnessapp.api.entities.MuscularGroup;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Named;
import javax.ws.rs.core.GenericType;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Jordi
 */
@Named(value = "dailyAdvancedWorkoutCreateView")
@ViewScoped
public class DailyAdvancedWorkoutCreateView implements Serializable{
    private Integer workoutId;
    
    private List<AdvancedExercise> mondayAdvancedExercises = new ArrayList<AdvancedExercise>();
    private List<AdvancedExercise> mondayAbleExercises = new ArrayList<AdvancedExercise>();
    private List<AdvancedExercise> mondaySelectedExercises = new ArrayList<AdvancedExercise>();
    
    private List<AdvancedExercise> tuesdayAdvancedExercises = new ArrayList<AdvancedExercise>();
    private List<AdvancedExercise> tuesdayAbleExercises = new ArrayList<AdvancedExercise>();
    private List<AdvancedExercise> tuesdaySelectedExercises = new ArrayList<AdvancedExercise>();
    
    private List<AdvancedExercise> wednesdayAdvancedExercises = new ArrayList<AdvancedExercise>();
    private List<AdvancedExercise> wednesdayAbleExercises = new ArrayList<AdvancedExercise>();
    private List<AdvancedExercise> wednesdaySelectedExercises = new ArrayList<AdvancedExercise>();
    
    private List<AdvancedExercise> thursdayAdvancedExercises = new ArrayList<AdvancedExercise>();
    private List<AdvancedExercise> thursdayAbleExercises = new ArrayList<AdvancedExercise>();
    private List<AdvancedExercise> thursdaySelectedExercises = new ArrayList<AdvancedExercise>();
    
    private List<AdvancedExercise> fridayAdvancedExercises = new ArrayList<AdvancedExercise>();
    private List<AdvancedExercise> fridayAbleExercises = new ArrayList<AdvancedExercise>();
    private List<AdvancedExercise> fridaySelectedExercises = new ArrayList<AdvancedExercise>();
    
    private AdvancedExerciseClient advancedExerciseClient = new AdvancedExerciseClient();
    
    private List<MuscularGroup> muscularGroupOptions = new ArrayList<MuscularGroup>();
    private MuscularGroupClient muscularGroupClient = new MuscularGroupClient();
    private MuscularGroup muscularGroup = new MuscularGroup();
    
    private DailyAdvancedWorkoutClient dailyAdvancedWorkoutClient = new DailyAdvancedWorkoutClient();
    private DailyAdvancedWorkout mondayDaily = new DailyAdvancedWorkout();
    private DailyAdvancedWorkout tuesdayDaily = new DailyAdvancedWorkout();
    private DailyAdvancedWorkout wednesdayDaily = new DailyAdvancedWorkout();
    private DailyAdvancedWorkout thursdayDaily = new DailyAdvancedWorkout();
    private DailyAdvancedWorkout fridayDaily = new DailyAdvancedWorkout();
    
    private AdvancedWorkoutClient advancedWorkoutClient = new AdvancedWorkoutClient();
    private boolean renderMonday;
    private boolean renderTuesday;
    private boolean renderWednesday;
    private boolean renderThursday;
    private boolean renderFriday;
    
    @PostConstruct
    public void init() {

    }
    
    public Integer getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Integer workoutId) {
        this.workoutId = workoutId;
    }

    public List<AdvancedExercise> getMondayAdvancedExercises() {
        return mondayAdvancedExercises;
    }

    public void setMondayAdvancedExercises(List<AdvancedExercise> mondayAdvancedExercises) {
        this.mondayAdvancedExercises = mondayAdvancedExercises;
    }

    public List<MuscularGroup> getMuscularGroupOptions() {
        return muscularGroupOptions;
    }

    public void setMuscularGroupOptions(List<MuscularGroup> muscularGroupOptions) {
        this.muscularGroupOptions = muscularGroupOptions;
    }

    public MuscularGroup getMuscularGroup() {
        return muscularGroup;
    }

    public void setMuscularGroup(MuscularGroup muscularGroup) {
        this.muscularGroup = muscularGroup;
    }

    public List<AdvancedExercise> getMondayAbleExercises() {
        return mondayAbleExercises;
    }

    public void setMondayAbleExercises(List<AdvancedExercise> mondayAbleExercises) {
        this.mondayAbleExercises = mondayAbleExercises;
    }

    public List<AdvancedExercise> getMondaySelectedExercises() {
        return mondaySelectedExercises;
    }

    public void setMondaySelectedExercises(List<AdvancedExercise> mondaySelectedExercises) {
        this.mondaySelectedExercises = mondaySelectedExercises;
    }

    public DailyAdvancedWorkout getMondayDaily() {
        return mondayDaily;
    }

    public void setMondayDaily(DailyAdvancedWorkout mondayDaily) {
        this.mondayDaily = mondayDaily;
    }

    public List<AdvancedExercise> getTuesdayAdvancedExercises() {
        return tuesdayAdvancedExercises;
    }

    public void setTuesdayAdvancedExercises(List<AdvancedExercise> tuesdayAdvancedExercises) {
        this.tuesdayAdvancedExercises = tuesdayAdvancedExercises;
    }

    public List<AdvancedExercise> getTuesdayAbleExercises() {
        return tuesdayAbleExercises;
    }

    public void setTuesdayAbleExercises(List<AdvancedExercise> tuesdayAbleExercises) {
        this.tuesdayAbleExercises = tuesdayAbleExercises;
    }

    public List<AdvancedExercise> getTuesdaySelectedExercises() {
        return tuesdaySelectedExercises;
    }

    public void setTuesdaySelectedExercises(List<AdvancedExercise> tuesdaySelectedExercises) {
        this.tuesdaySelectedExercises = tuesdaySelectedExercises;
    }

    public List<AdvancedExercise> getWednesdayAdvancedExercises() {
        return wednesdayAdvancedExercises;
    }

    public void setWednesdayAdvancedExercises(List<AdvancedExercise> wednesdayAdvancedExercises) {
        this.wednesdayAdvancedExercises = wednesdayAdvancedExercises;
    }

    public List<AdvancedExercise> getWednesdayAbleExercises() {
        return wednesdayAbleExercises;
    }

    public void setWednesdayAbleExercises(List<AdvancedExercise> wednesdayAbleExercises) {
        this.wednesdayAbleExercises = wednesdayAbleExercises;
    }

    public List<AdvancedExercise> getWednesdaySelectedExercises() {
        return wednesdaySelectedExercises;
    }

    public void setWednesdaySelectedExercises(List<AdvancedExercise> wednesdaySelectedExercises) {
        this.wednesdaySelectedExercises = wednesdaySelectedExercises;
    }

    public List<AdvancedExercise> getThursdayAdvancedExercises() {
        return thursdayAdvancedExercises;
    }

    public void setThursdayAdvancedExercises(List<AdvancedExercise> thursdayAdvancedExercises) {
        this.thursdayAdvancedExercises = thursdayAdvancedExercises;
    }

    public List<AdvancedExercise> getThursdayAbleExercises() {
        return thursdayAbleExercises;
    }

    public void setThursdayAbleExercises(List<AdvancedExercise> thursdayAbleExercises) {
        this.thursdayAbleExercises = thursdayAbleExercises;
    }

    public List<AdvancedExercise> getThursdaySelectedExercises() {
        return thursdaySelectedExercises;
    }

    public void setThursdaySelectedExercises(List<AdvancedExercise> thursdaySelectedExercises) {
        this.thursdaySelectedExercises = thursdaySelectedExercises;
    }

    public List<AdvancedExercise> getFridayAdvancedExercises() {
        return fridayAdvancedExercises;
    }

    public void setFridayAdvancedExercises(List<AdvancedExercise> fridayAdvancedExercises) {
        this.fridayAdvancedExercises = fridayAdvancedExercises;
    }

    public List<AdvancedExercise> getFridayAbleExercises() {
        return fridayAbleExercises;
    }

    public void setFridayAbleExercises(List<AdvancedExercise> fridayAbleExercises) {
        this.fridayAbleExercises = fridayAbleExercises;
    }

    public List<AdvancedExercise> getFridaySelectedExercises() {
        return fridaySelectedExercises;
    }

    public void setFridaySelectedExercises(List<AdvancedExercise> fridaySelectedExercises) {
        this.fridaySelectedExercises = fridaySelectedExercises;
    }

    public DailyAdvancedWorkout getTuesdayDaily() {
        return tuesdayDaily;
    }

    public void setTuesdayDaily(DailyAdvancedWorkout tuesdayDaily) {
        this.tuesdayDaily = tuesdayDaily;
    }

    public DailyAdvancedWorkout getWednesdayDaily() {
        return wednesdayDaily;
    }

    public void setWednesdayDaily(DailyAdvancedWorkout wednesdayDaily) {
        this.wednesdayDaily = wednesdayDaily;
    }

    public DailyAdvancedWorkout getThursdayDaily() {
        return thursdayDaily;
    }

    public void setThursdayDaily(DailyAdvancedWorkout thursdayDaily) {
        this.thursdayDaily = thursdayDaily;
    }

    public DailyAdvancedWorkout getFridayDaily() {
        return fridayDaily;
    }

    public void setFridayDaily(DailyAdvancedWorkout fridayDaily) {
        this.fridayDaily = fridayDaily;
    }

    public boolean isRenderMonday() {
        return renderMonday;
    }

    public void setRenderMonday(boolean renderMonday) {
        this.renderMonday = renderMonday;
    }

    public boolean isRenderTuesday() {
        return renderTuesday;
    }

    public void setRenderTuesday(boolean renderTuesday) {
        this.renderTuesday = renderTuesday;
    }

    public boolean isRenderWednesday() {
        return renderWednesday;
    }

    public void setRenderWednesday(boolean renderWednesday) {
        this.renderWednesday = renderWednesday;
    }

    public boolean isRenderThursday() {
        return renderThursday;
    }

    public void setRenderThursday(boolean renderThursday) {
        this.renderThursday = renderThursday;
    }

    public boolean isRenderFriday() {
        return renderFriday;
    }

    public void setRenderFriday(boolean renderFriday) {
        this.renderFriday = renderFriday;
    }
    
    public void getValues() {
        FaceletContext faceletContext = (FaceletContext) FacesContext.getCurrentInstance().getAttributes().get(FaceletContext.FACELET_CONTEXT_KEY);
        workoutId = (Integer) faceletContext.getAttribute("workoutId");
        
        List<DailyAdvancedWorkout> tmp;
        tmp = dailyAdvancedWorkoutClient.findByAdvancedWorkoutId(new GenericType<List<DailyAdvancedWorkout>>() {
        }, workoutId.toString());
        
        for(DailyAdvancedWorkout daw : tmp){
            if(daw.getWeek_day() == 1){
                mondayDaily = daw;
            }
            if (daw.getWeek_day() == 2) {
                tuesdayDaily = daw;
            }
            if (daw.getWeek_day() == 3) {
                wednesdayDaily = daw;
            }
            if (daw.getWeek_day() == 4) {
                thursdayDaily = daw;
            }
            if (daw.getWeek_day() == 5) {
                fridayDaily = daw;
            }
        }
        
        AdvancedWorkout tmpAW ;
        tmpAW = advancedWorkoutClient.find(AdvancedWorkout.class, workoutId.toString());
        if(tmpAW.getDuration()==5){
            renderMonday = true;
            renderTuesday = true;
            renderWednesday = true;
            renderThursday = true;
            renderFriday = true;
        }else if(tmpAW.getDuration() == 3){
            renderMonday = true;
            renderWednesday = true;
            renderFriday = true;
        }
    }

    public void loadFilterOptions() {
        muscularGroupOptions = muscularGroupClient.findAll(new GenericType<List<MuscularGroup>>() {
        });
    }

    public void recuperarValorCampMonday(AjaxBehaviorEvent e) {
        //assign new value to localeCode
        Integer idMG = (Integer) ((UIOutput) e.getSource()).getValue();
        //muscularGroup = muscularGroupClient.find(MuscularGroup.class, idMG.toString());

        List<AdvancedExercise> tmp;
        tmp = advancedExerciseClient.findByMuscularGroupId(new GenericType<List<AdvancedExercise>>() {
        }, String.valueOf(idMG));
        if(null!=tmp && tmp.size()>0){
            mondayAbleExercises.clear();
            for(AdvancedExercise q : tmp){
                if(!mondayAdvancedExercises.contains(q)){
                    mondayAbleExercises.add(q);
                }
            }
        }
    }
    public void recuperarValorCampTuesday(AjaxBehaviorEvent e) {
        //assign new value to localeCode
        Integer idMG = (Integer) ((UIOutput) e.getSource()).getValue();
        //muscularGroup = muscularGroupClient.find(MuscularGroup.class, idMG.toString());

        List<AdvancedExercise> tmp;
        tmp = advancedExerciseClient.findByMuscularGroupId(new GenericType<List<AdvancedExercise>>() {
        }, String.valueOf(idMG));
        if (null != tmp && tmp.size() > 0) {
            tuesdayAbleExercises.clear();
            for (AdvancedExercise q : tmp) {
                if (!tuesdayAdvancedExercises.contains(q)) {
                    tuesdayAbleExercises.add(q);
                }
            }
        }
    }
    public void recuperarValorCampWednesday(AjaxBehaviorEvent e) {
        //assign new value to localeCode
        Integer idMG = (Integer) ((UIOutput) e.getSource()).getValue();
        //muscularGroup = muscularGroupClient.find(MuscularGroup.class, idMG.toString());

        List<AdvancedExercise> tmp;
        tmp = advancedExerciseClient.findByMuscularGroupId(new GenericType<List<AdvancedExercise>>() {
        }, String.valueOf(idMG));
        if (null != tmp && tmp.size() > 0) {
            wednesdayAbleExercises.clear();
            for (AdvancedExercise q : tmp) {
                if (!wednesdayAdvancedExercises.contains(q)) {
                    wednesdayAbleExercises.add(q);
                }
            }
        }
    }
    public void recuperarValorCampThursday(AjaxBehaviorEvent e) {
        //assign new value to localeCode
        Integer idMG = (Integer) ((UIOutput) e.getSource()).getValue();
        //muscularGroup = muscularGroupClient.find(MuscularGroup.class, idMG.toString());

        List<AdvancedExercise> tmp;
        tmp = advancedExerciseClient.findByMuscularGroupId(new GenericType<List<AdvancedExercise>>() {
        }, String.valueOf(idMG));
        if (null != tmp && tmp.size() > 0) {
            thursdayAbleExercises.clear();
            for (AdvancedExercise q : tmp) {
                if (!thursdayAdvancedExercises.contains(q)) {
                    thursdayAbleExercises.add(q);
                }
            }
        }
    }
    public void recuperarValorCampFriday(AjaxBehaviorEvent e) {
        //assign new value to localeCode
        Integer idMG = (Integer) ((UIOutput) e.getSource()).getValue();
        //muscularGroup = muscularGroupClient.find(MuscularGroup.class, idMG.toString());

        List<AdvancedExercise> tmp;
        tmp = advancedExerciseClient.findByMuscularGroupId(new GenericType<List<AdvancedExercise>>() {
        }, String.valueOf(idMG));
        if (null != tmp && tmp.size() > 0) {
            fridayAbleExercises.clear();
            for (AdvancedExercise q : tmp) {
                if (!fridayAdvancedExercises.contains(q)) {
                    fridayAbleExercises.add(q);
                }
            }
        }
    }
    
    public void addMondayExercises(){
        for (AdvancedExercise q : mondaySelectedExercises) {
                mondayAdvancedExercises.add(q);            
        }
        mondayAbleExercises.clear();
        
    }
    public void addTuesdayExercises() {
        for (AdvancedExercise q : tuesdaySelectedExercises) {
            tuesdayAdvancedExercises.add(q);
        }
        tuesdayAbleExercises.clear();

    }
    public void addWednesdayExercises() {
        for (AdvancedExercise q : wednesdaySelectedExercises) {
            wednesdayAdvancedExercises.add(q);
        }
        wednesdayAbleExercises.clear();

    }
    public void addThursdayExercises() {
        for (AdvancedExercise q : thursdaySelectedExercises) {
            thursdayAdvancedExercises.add(q);
        }
        thursdayAbleExercises.clear();

    }
    public void addFridayExercises() {
        for (AdvancedExercise q :fridaySelectedExercises) {
            fridayAdvancedExercises.add(q);
        }
        fridayAbleExercises.clear();

    }
    
    public void create(){
        try{
            if(mondayAdvancedExercises.size() > 0){
                Set<AdvancedExercise> tmp = new HashSet<AdvancedExercise>(mondayAdvancedExercises);
                mondayDaily.setAdvancedExercises(tmp);
                dailyAdvancedWorkoutClient.edit(mondayDaily, mondayDaily.getId().toString());
            }
            if (tuesdayAdvancedExercises.size() > 0) {
                Set<AdvancedExercise> tmp = new HashSet<AdvancedExercise>(tuesdayAdvancedExercises);
                tuesdayDaily.setAdvancedExercises(tmp);
                dailyAdvancedWorkoutClient.edit(tuesdayDaily, tuesdayDaily.getId().toString());
            }
            if (wednesdayAdvancedExercises.size() > 0) {
                Set<AdvancedExercise> tmp = new HashSet<AdvancedExercise>(wednesdayAdvancedExercises);
                wednesdayDaily.setAdvancedExercises(tmp);
                dailyAdvancedWorkoutClient.edit(wednesdayDaily, wednesdayDaily.getId().toString());
            }
            if (thursdayAdvancedExercises.size() > 0) {
                Set<AdvancedExercise> tmp = new HashSet<AdvancedExercise>(thursdayAdvancedExercises);
                thursdayDaily.setAdvancedExercises(tmp);
                dailyAdvancedWorkoutClient.edit(thursdayDaily, thursdayDaily.getId().toString());
            }
            if (fridayAdvancedExercises.size() > 0) {
                Set<AdvancedExercise> tmp = new HashSet<AdvancedExercise>(fridayAdvancedExercises);
                fridayDaily.setAdvancedExercises(tmp);
                dailyAdvancedWorkoutClient.edit(fridayDaily, fridayDaily.getId().toString());
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Created correctly", null));
            FacesContext.getCurrentInstance().getExternalContext().redirect("menu.xhtml");
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR on assigning exercises to dailies!", null));
        }
    }
}
