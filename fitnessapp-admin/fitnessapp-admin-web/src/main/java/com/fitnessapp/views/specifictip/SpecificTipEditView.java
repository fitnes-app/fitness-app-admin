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
package com.fitnessapp.views.specifictip;

import com.fitnessapp.api.client.MuscularGroupClient;
import com.fitnessapp.api.client.SpecificTipClient;
import com.fitnessapp.api.entities.MuscularGroup;
import com.fitnessapp.views.advancedexercise.*;
import com.fitnessapp.api.entities.SpecificTip;
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

/**
 *
 * @author Naluem
 */
@Named(value = "specificTipEditView")
@ViewScoped
public class SpecificTipEditView implements Serializable {

    private List<SpecificTip> specificTips = new ArrayList<SpecificTip>();
    private SpecificTip st = new SpecificTip();
    private SpecificTipClient stc = new SpecificTipClient();

    private List<MuscularGroup> muscularGroupOptions = new ArrayList<MuscularGroup>();
    private MuscularGroupClient muscularGroupClient = new MuscularGroupClient();
    private MuscularGroup muscularGroup = new MuscularGroup();
    
    private boolean mgHasChanged=false;
    
    @PostConstruct
    public void init() {
        specificTips = stc.findAll(new GenericType<List<SpecificTip>>() {
        });
        muscularGroupOptions = muscularGroupClient.findAll(new GenericType<List<MuscularGroup>>() {
        });
    }

    public List<SpecificTip> getSpecificTips() {
        return specificTips;
    }

    public void setSpecificTips(List<SpecificTip> specificTips) {
        this.specificTips = specificTips;
    }

    public SpecificTip getSt() {
        return st;
    }

    public void setSt(SpecificTip st) {
        this.st = st;
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
        st = (SpecificTip) event.getObject();
        if (mgHasChanged) {
            st.setMuscularGroupId(muscularGroup);
        }
        stc.edit(st, st.getId().toString());
        
        FacesMessage msg = new FacesMessage("SpecificTipEdited", "");
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
        Integer idMuscularGroup = (Integer) ((UIOutput) e.getSource()).getValue();
        muscularGroup = muscularGroupClient.find(MuscularGroup.class, idMuscularGroup.toString());

        FacesContext facesContext = FacesContext.getCurrentInstance();
        int idTip = Integer.parseInt(facesContext.getExternalContext().getRequestParameterMap().get("idTip"));

        for (SpecificTip q : specificTips) {
            if (q.getId().equals(idTip)) {
                q.setMuscularGroupId(muscularGroup);
                mgHasChanged = true;
                break;
            }
        }
    }
//    public void delete() {
//        try {
//            SpecificTipClient dtc = new SpecificTipClient();
//            FacesContext facesContext = FacesContext.getCurrentInstance();
//            String idT = (String) facesContext.getExternalContext().getRequestParameterMap().get("idT");
//
//            if (idT != null && !"".equals(idT)) {
//                dtc.remove(idT);
//                specificTips = dtc.findAll(new GenericType<List<SpecificTip>>() {
//                });;
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
