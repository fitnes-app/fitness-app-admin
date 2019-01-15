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
package com.fitnessapp.views.dailybasicworkout;

import com.fitnessapp.api.client.AdvancedExerciseClient;
import com.fitnessapp.api.client.AdvancedWorkoutClient;
import com.fitnessapp.api.client.BasicExerciseClient;
import com.fitnessapp.api.client.BasicWorkoutClient;
import com.fitnessapp.api.client.DailyAdvancedWorkoutClient;
import com.fitnessapp.api.client.DailyBasicWorkoutClient;
import com.fitnessapp.api.client.MuscularGroupClient;
import com.fitnessapp.api.entities.AdvancedExercise;
import com.fitnessapp.api.entities.AdvancedWorkout;
import com.fitnessapp.api.entities.BasicExercise;
import com.fitnessapp.api.entities.BasicWorkout;
import com.fitnessapp.api.entities.DailyAdvancedWorkout;
import com.fitnessapp.api.entities.DailyBasicWorkout;
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

/**
 *
 * @author Jordi
 */
@Named(value = "dailyBasicWorkoutCreateView")
@ViewScoped
public class DailyBasicWorkoutCreateView implements Serializable {

    private Integer workoutId;

    private List<BasicExercise> mondayBasicExercises = new ArrayList<BasicExercise>();
    private List<BasicExercise> mondayAbleExercises = new ArrayList<BasicExercise>();
    private List<BasicExercise> mondaySelectedExercises = new ArrayList<BasicExercise>();

    private List<BasicExercise> tuesdayBasicExercises = new ArrayList<BasicExercise>();
    private List<BasicExercise> tuesdayAbleExercises = new ArrayList<BasicExercise>();
    private List<BasicExercise> tuesdaySelectedExercises = new ArrayList<BasicExercise>();

    private List<BasicExercise> wednesdayBasicExercises = new ArrayList<BasicExercise>();
    private List<BasicExercise> wednesdayAbleExercises = new ArrayList<BasicExercise>();
    private List<BasicExercise> wednesdaySelectedExercises = new ArrayList<BasicExercise>();

    private List<BasicExercise> thursdayBasicExercises = new ArrayList<BasicExercise>();
    private List<BasicExercise> thursdayAbleExercises = new ArrayList<BasicExercise>();
    private List<BasicExercise> thursdaySelectedExercises = new ArrayList<BasicExercise>();

    private List<BasicExercise> fridayBasicExercises = new ArrayList<BasicExercise>();
    private List<BasicExercise> fridayAbleExercises = new ArrayList<BasicExercise>();
    private List<BasicExercise> fridaySelectedExercises = new ArrayList<BasicExercise>();

    private BasicExerciseClient basicExerciseClient = new BasicExerciseClient();

    private List<MuscularGroup> muscularGroupOptions = new ArrayList<MuscularGroup>();
    private MuscularGroupClient muscularGroupClient = new MuscularGroupClient();
    private MuscularGroup muscularGroup = new MuscularGroup();

    private DailyBasicWorkoutClient dailyBasicWorkoutClient = new DailyBasicWorkoutClient();
    private DailyBasicWorkout mondayDaily = new DailyBasicWorkout();
    private DailyBasicWorkout tuesdayDaily = new DailyBasicWorkout();
    private DailyBasicWorkout wednesdayDaily = new DailyBasicWorkout();
    private DailyBasicWorkout thursdayDaily = new DailyBasicWorkout();
    private DailyBasicWorkout fridayDaily = new DailyBasicWorkout();

    private BasicWorkoutClient basicWorkoutClient = new BasicWorkoutClient();
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

    public List<BasicExercise> getMondayBasicExercises() {
        return mondayBasicExercises;
    }

    public void setMondayBasicExercises(List<BasicExercise> mondayBasicExercises) {
        this.mondayBasicExercises = mondayBasicExercises;
    }

    public List<BasicExercise> getMondayAbleExercises() {
        return mondayAbleExercises;
    }

    public void setMondayAbleExercises(List<BasicExercise> mondayAbleExercises) {
        this.mondayAbleExercises = mondayAbleExercises;
    }

    public List<BasicExercise> getMondaySelectedExercises() {
        return mondaySelectedExercises;
    }

    public void setMondaySelectedExercises(List<BasicExercise> mondaySelectedExercises) {
        this.mondaySelectedExercises = mondaySelectedExercises;
    }

    public List<BasicExercise> getTuesdayBasicExercises() {
        return tuesdayBasicExercises;
    }

    public void setTuesdayBasicExercises(List<BasicExercise> tuesdayBasicExercises) {
        this.tuesdayBasicExercises = tuesdayBasicExercises;
    }

    public List<BasicExercise> getTuesdayAbleExercises() {
        return tuesdayAbleExercises;
    }

    public void setTuesdayAbleExercises(List<BasicExercise> tuesdayAbleExercises) {
        this.tuesdayAbleExercises = tuesdayAbleExercises;
    }

    public List<BasicExercise> getTuesdaySelectedExercises() {
        return tuesdaySelectedExercises;
    }

    public void setTuesdaySelectedExercises(List<BasicExercise> tuesdaySelectedExercises) {
        this.tuesdaySelectedExercises = tuesdaySelectedExercises;
    }

    public List<BasicExercise> getWednesdayBasicExercises() {
        return wednesdayBasicExercises;
    }

    public void setWednesdayBasicExercises(List<BasicExercise> wednesdayBasicExercises) {
        this.wednesdayBasicExercises = wednesdayBasicExercises;
    }

    public List<BasicExercise> getWednesdayAbleExercises() {
        return wednesdayAbleExercises;
    }

    public void setWednesdayAbleExercises(List<BasicExercise> wednesdayAbleExercises) {
        this.wednesdayAbleExercises = wednesdayAbleExercises;
    }

    public List<BasicExercise> getWednesdaySelectedExercises() {
        return wednesdaySelectedExercises;
    }

    public void setWednesdaySelectedExercises(List<BasicExercise> wednesdaySelectedExercises) {
        this.wednesdaySelectedExercises = wednesdaySelectedExercises;
    }

    public List<BasicExercise> getThursdayBasicExercises() {
        return thursdayBasicExercises;
    }

    public void setThursdayBasicExercises(List<BasicExercise> thursdayBasicExercises) {
        this.thursdayBasicExercises = thursdayBasicExercises;
    }

    public List<BasicExercise> getThursdayAbleExercises() {
        return thursdayAbleExercises;
    }

    public void setThursdayAbleExercises(List<BasicExercise> thursdayAbleExercises) {
        this.thursdayAbleExercises = thursdayAbleExercises;
    }

    public List<BasicExercise> getThursdaySelectedExercises() {
        return thursdaySelectedExercises;
    }

    public void setThursdaySelectedExercises(List<BasicExercise> thursdaySelectedExercises) {
        this.thursdaySelectedExercises = thursdaySelectedExercises;
    }

    public List<BasicExercise> getFridayBasicExercises() {
        return fridayBasicExercises;
    }

    public void setFridayBasicExercises(List<BasicExercise> fridayBasicExercises) {
        this.fridayBasicExercises = fridayBasicExercises;
    }

    public List<BasicExercise> getFridayAbleExercises() {
        return fridayAbleExercises;
    }

    public void setFridayAbleExercises(List<BasicExercise> fridayAbleExercises) {
        this.fridayAbleExercises = fridayAbleExercises;
    }

    public List<BasicExercise> getFridaySelectedExercises() {
        return fridaySelectedExercises;
    }

    public void setFridaySelectedExercises(List<BasicExercise> fridaySelectedExercises) {
        this.fridaySelectedExercises = fridaySelectedExercises;
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

    public DailyBasicWorkout getMondayDaily() {
        return mondayDaily;
    }

    public void setMondayDaily(DailyBasicWorkout mondayDaily) {
        this.mondayDaily = mondayDaily;
    }

    public DailyBasicWorkout getTuesdayDaily() {
        return tuesdayDaily;
    }

    public void setTuesdayDaily(DailyBasicWorkout tuesdayDaily) {
        this.tuesdayDaily = tuesdayDaily;
    }

    public DailyBasicWorkout getWednesdayDaily() {
        return wednesdayDaily;
    }

    public void setWednesdayDaily(DailyBasicWorkout wednesdayDaily) {
        this.wednesdayDaily = wednesdayDaily;
    }

    public DailyBasicWorkout getThursdayDaily() {
        return thursdayDaily;
    }

    public void setThursdayDaily(DailyBasicWorkout thursdayDaily) {
        this.thursdayDaily = thursdayDaily;
    }

    public DailyBasicWorkout getFridayDaily() {
        return fridayDaily;
    }

    public void setFridayDaily(DailyBasicWorkout fridayDaily) {
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

        List<DailyBasicWorkout> tmp;
        tmp = dailyBasicWorkoutClient.findByBasicWorkoutId(new GenericType<List<DailyBasicWorkout>>() {
        }, workoutId.toString());

        for (DailyBasicWorkout daw : tmp) {
            if (daw.getWeek_day() == 1) {
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

        BasicWorkout tmpAW;
        tmpAW = basicWorkoutClient.find(BasicWorkout.class, workoutId.toString());
        if (tmpAW.getDuration() == 5) {
            renderMonday = true;
            renderTuesday = true;
            renderWednesday = true;
            renderThursday = true;
            renderFriday = true;
        } else if (tmpAW.getDuration() == 3) {
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

        List<BasicExercise> tmp;
        tmp = basicExerciseClient.findByMuscularGroupId(new GenericType<List<BasicExercise>>() {
        }, String.valueOf(idMG));
        if (null != tmp && tmp.size() > 0) {
            mondayAbleExercises.clear();
            for (BasicExercise q : tmp) {
                if (!mondayBasicExercises.contains(q)) {
                    mondayAbleExercises.add(q);
                }
            }
        }
    }

    public void recuperarValorCampTuesday(AjaxBehaviorEvent e) {
        //assign new value to localeCode
        Integer idMG = (Integer) ((UIOutput) e.getSource()).getValue();
        //muscularGroup = muscularGroupClient.find(MuscularGroup.class, idMG.toString());

        List<BasicExercise> tmp;
        tmp = basicExerciseClient.findByMuscularGroupId(new GenericType<List<BasicExercise>>() {
        }, String.valueOf(idMG));
        if (null != tmp && tmp.size() > 0) {
            tuesdayAbleExercises.clear();
            for (BasicExercise q : tmp) {
                if (!tuesdayBasicExercises.contains(q)) {
                    tuesdayAbleExercises.add(q);
                }
            }
        }
    }

    public void recuperarValorCampWednesday(AjaxBehaviorEvent e) {
        //assign new value to localeCode
        Integer idMG = (Integer) ((UIOutput) e.getSource()).getValue();
        //muscularGroup = muscularGroupClient.find(MuscularGroup.class, idMG.toString());

        List<BasicExercise> tmp;
        tmp = basicExerciseClient.findByMuscularGroupId(new GenericType<List<BasicExercise>>() {
        }, String.valueOf(idMG));
        if (null != tmp && tmp.size() > 0) {
            wednesdayAbleExercises.clear();
            for (BasicExercise q : tmp) {
                if (!wednesdayBasicExercises.contains(q)) {
                    wednesdayAbleExercises.add(q);
                }
            }
        }
    }

    public void recuperarValorCampThursday(AjaxBehaviorEvent e) {
        //assign new value to localeCode
        Integer idMG = (Integer) ((UIOutput) e.getSource()).getValue();
        //muscularGroup = muscularGroupClient.find(MuscularGroup.class, idMG.toString());

        List<BasicExercise> tmp;
        tmp = basicExerciseClient.findByMuscularGroupId(new GenericType<List<BasicExercise>>() {
        }, String.valueOf(idMG));
        if (null != tmp && tmp.size() > 0) {
            thursdayAbleExercises.clear();
            for (BasicExercise q : tmp) {
                if (!thursdayBasicExercises.contains(q)) {
                    thursdayAbleExercises.add(q);
                }
            }
        }
    }

    public void recuperarValorCampFriday(AjaxBehaviorEvent e) {
        //assign new value to localeCode
        Integer idMG = (Integer) ((UIOutput) e.getSource()).getValue();
        //muscularGroup = muscularGroupClient.find(MuscularGroup.class, idMG.toString());

        List<BasicExercise> tmp;
        tmp = basicExerciseClient.findByMuscularGroupId(new GenericType<List<BasicExercise>>() {
        }, String.valueOf(idMG));
        if (null != tmp && tmp.size() > 0) {
            fridayAbleExercises.clear();
            for (BasicExercise q : tmp) {
                if (!fridayBasicExercises.contains(q)) {
                    fridayAbleExercises.add(q);
                }
            }
        }
    }
    
    public void addMondayExercises() {
        for (BasicExercise q : mondaySelectedExercises) {
            mondayBasicExercises.add(q);
        }
        mondayAbleExercises.clear();

    }

    public void addTuesdayExercises() {
        for (BasicExercise q : tuesdaySelectedExercises) {
            tuesdayBasicExercises.add(q);
        }
        tuesdayAbleExercises.clear();

    }

    public void addWednesdayExercises() {
        for (BasicExercise q : wednesdaySelectedExercises) {
            wednesdayBasicExercises.add(q);
        }
        wednesdayAbleExercises.clear();

    }

    public void addThursdayExercises() {
        for (BasicExercise q : thursdaySelectedExercises) {
            thursdayBasicExercises.add(q);
        }
        thursdayAbleExercises.clear();

    }

    public void addFridayExercises() {
        for (BasicExercise q : fridaySelectedExercises) {
            fridayBasicExercises.add(q);
        }
        fridayAbleExercises.clear();

    }
    
    public void create() {
        try {
            if (mondayBasicExercises.size() > 0) {
                Set<BasicExercise> tmp = new HashSet<BasicExercise>(mondayBasicExercises);
                mondayDaily.setBasicExercises(tmp);
                dailyBasicWorkoutClient.edit(mondayDaily, mondayDaily.getId().toString());
            }
            if (tuesdayBasicExercises.size() > 0) {
                Set<BasicExercise> tmp = new HashSet<BasicExercise>(tuesdayBasicExercises);
                tuesdayDaily.setBasicExercises(tmp);
                dailyBasicWorkoutClient.edit(tuesdayDaily, tuesdayDaily.getId().toString());
            }
            if (wednesdayBasicExercises.size() > 0) {
                Set<BasicExercise> tmp = new HashSet<BasicExercise>(wednesdayBasicExercises);
                wednesdayDaily.setBasicExercises(tmp);
                dailyBasicWorkoutClient.edit(wednesdayDaily, wednesdayDaily.getId().toString());
            }
            if (thursdayBasicExercises.size() > 0) {
                Set<BasicExercise> tmp = new HashSet<BasicExercise>(thursdayBasicExercises);
                thursdayDaily.setBasicExercises(tmp);
                dailyBasicWorkoutClient.edit(thursdayDaily, thursdayDaily.getId().toString());
            }
            if (fridayBasicExercises.size() > 0) {
                Set<BasicExercise> tmp = new HashSet<BasicExercise>(fridayBasicExercises);
                fridayDaily.setBasicExercises(tmp);
                dailyBasicWorkoutClient.edit(fridayDaily, fridayDaily.getId().toString());
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Created correctly", null));
            FacesContext.getCurrentInstance().getExternalContext().redirect("menu.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR on assigning exercises to dailies!", null));
        }
    }
}
