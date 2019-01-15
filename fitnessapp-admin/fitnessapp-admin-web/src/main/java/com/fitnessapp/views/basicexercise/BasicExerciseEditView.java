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
import com.fitnessapp.api.client.MuscularGroupClient;
import com.fitnessapp.api.entities.BasicExercise;
import com.fitnessapp.api.entities.MuscularGroup;
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

    private List<BasicExercise> basicExercises = new ArrayList<BasicExercise>();
    private BasicExercise be = new BasicExercise();
    private BasicExerciseClient basicExerciseClient = new BasicExerciseClient();
    
    private List<MuscularGroup> muscularGroupOptions = new ArrayList<MuscularGroup>();
    private MuscularGroupClient muscularGroupClient = new MuscularGroupClient();
    private MuscularGroup muscularGroup = new MuscularGroup();
    
    private boolean mgHasChanged=false;

    @PostConstruct
    public void init() {
        basicExercises = basicExerciseClient.findAll(new GenericType<List<BasicExercise>>() {
        });
        muscularGroupOptions = muscularGroupClient.findAll(new GenericType<List<MuscularGroup>>() {
        });
    }

    public List<BasicExercise> getBasicExercises() {
        return basicExercises;
    }

    public void setBasicExercises(List<BasicExercise> basicExercises) {
        this.basicExercises = basicExercises;
    }

    public BasicExercise getBe() {
        return be;
    }

    public void setBe(BasicExercise be) {
        this.be = be;
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

    public boolean isMgHasChanged() {
        return mgHasChanged;
    }

    public void setMgHasChanged(boolean mgHasChanged) {
        this.mgHasChanged = mgHasChanged;
    }


    public void onRowEdit(RowEditEvent event) {
        be = (BasicExercise) event.getObject();
        if(mgHasChanged){
            be.setMuscularGroupId(muscularGroup);
        }
        basicExerciseClient.edit((be), be.getId().toString());
        
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
    public void recuperarValorCamp(AjaxBehaviorEvent e) {
        //assign new value to localeCode
        Integer idMG = (Integer) ((UIOutput) e.getSource()).getValue();
        muscularGroup = muscularGroupClient.find(MuscularGroup.class, idMG.toString());

        FacesContext facesContext = FacesContext.getCurrentInstance();
        int idExercise = Integer.parseInt(facesContext.getExternalContext().getRequestParameterMap().get("idExercise"));

        for (BasicExercise q : basicExercises) {
            if (q.getId().equals(idExercise)) {
                q.setMuscularGroupId(muscularGroup);
                mgHasChanged = true;
                break;
            }
        }
    }
//    public void delete() {
//        try {
//            BasicExerciseClient bwc = new BasicExerciseClient();
//            FacesContext facesContext = FacesContext.getCurrentInstance();
//            String idT = (String) facesContext.getExternalContext().getRequestParameterMap().get("idT");
//
//            if (idT != null && !"".equals(idT)) {
//                bwc.remove(idT);
//                basicExercises = bwc.findAll(new GenericType<List<BasicExercise>>() {
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
