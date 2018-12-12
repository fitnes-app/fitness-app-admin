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
import javax.faces.context.FacesContext;
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

    private List<SpecificTip> specificTips;
    private List<MuscularGroup> muscularGroups;
    private SpecificTip st = new SpecificTip();
    private MuscularGroup mg = new MuscularGroup();

    @PostConstruct
    public void init() {
        specificTips = new ArrayList<SpecificTip>();
        specificTips = getSpecificTips();
        muscularGroups = getMuscularGroups();
    }

    public List<MuscularGroup> getMuscularGroups() {
        MuscularGroupClient client = new MuscularGroupClient();
        List<MuscularGroup> tmpGroups = client.findAll(new GenericType<List<MuscularGroup>>() {
        });
        client.close();
        return tmpGroups;
    }

    public void setMuscularGroups(List<MuscularGroup> muscularGroups) {
        this.muscularGroups = muscularGroups;
    }

    public MuscularGroup getMg() {
        return mg;
    }

    public void setMg(MuscularGroup mg) {
        this.mg = mg;
    }

    public List<SpecificTip> getSpecificTips() {
        SpecificTipClient stc = new SpecificTipClient();
        List<SpecificTip> sttmp = stc.findAll(new GenericType<List<SpecificTip>>() {
        });
        stc.close();
        return sttmp;
    }

    public void setSpecificTips(List<SpecificTip> specificTips) {
        this.specificTips = specificTips;
    }

    public void onRowEdit(RowEditEvent event) {
        SpecificTipClient stc = new SpecificTipClient();
        SpecificTip s = (SpecificTip) event.getObject();
        s.setMuscularGroupId(mg);
        stc.edit(s, s.getId().toString());
        stc.close();
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
    
    public void delete() {
        try {
            SpecificTipClient dtc = new SpecificTipClient();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String idT = (String) facesContext.getExternalContext().getRequestParameterMap().get("idT");

            if (idT != null && !"".equals(idT)) {
                dtc.remove(idT);
                specificTips = dtc.findAll(new GenericType<List<SpecificTip>>() {
                });;
            }

            FacesContext.getCurrentInstance().addMessage("llist", new FacesMessage(FacesMessage.SEVERITY_INFO, "Deletion succeed", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(
                    "llist",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR when deleting", null));
        }

    }

}
