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
package com.fitnessapp.views.dailytip;

import com.fitnessapp.api.client.DailyTipClient;
import com.fitnessapp.api.entities.DailyTip;
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
@Named(value = "dailyTipEditView")
@ViewScoped
public class DailyTipEditView implements Serializable {

    private DailyTipClient dtc = new DailyTipClient();
    private List<DailyTip> dailyTips;
    private DailyTip dt = new DailyTip();

    @PostConstruct
    public void init() {
        dailyTips = new ArrayList<DailyTip>();
        dailyTips = dtc.findAll(new GenericType<List<DailyTip>>() {
        });
    }

    public List<DailyTip> getDailyTips() {
        return dailyTips;
    }

    public void setDailyTips(List<DailyTip> dailyTips) {
        this.dailyTips = dailyTips;
    }

    public void onRowEdit(RowEditEvent event) {
        DailyTip dt = (DailyTip)event.getObject();
        dtc.edit(dt, dt.getId().toString());
        
        FacesMessage msg = new FacesMessage("DailyTipEdited", "");
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
            DailyTipClient dtc = new DailyTipClient();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String idT = (String) facesContext.getExternalContext().getRequestParameterMap().get("idT");

            if (idT != null && !"".equals(idT)) {
                dtc.remove(idT);
                dailyTips = dtc.findAll(new GenericType<List<DailyTip>>() {
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
