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

/**
 *
 * @author Naluem
 */
@Named(value = "specificTipCreateView")
@ViewScoped
public class SpecificTipCreateView implements Serializable {

    private String text;
    private Integer muscularGroupId;

    private SpecificTipClient stc = new SpecificTipClient();
    private List<Integer> mgroupIds;
    private SpecificTip st = new SpecificTip();
    private List<SpecificTip> stl = new ArrayList<SpecificTip>();

    @PostConstruct
    public void init() {
        stc = new SpecificTipClient();
        mgroupIds = getMgroupIds();
    }
    
    public List<Integer> getMgroupIds() {
        MuscularGroupClient mClient = new MuscularGroupClient();
        List<MuscularGroup> tmpBtype = mClient.findAll(new GenericType<List<MuscularGroup>>() {
        });
        List<Integer> tmpIds = new ArrayList<>();
        for (MuscularGroup t : tmpBtype) {
            tmpIds.add(t.getId());
        }
        return tmpIds;
    }

    public void setMgroupIds(List<Integer> mgroupIds) {
        this.mgroupIds = mgroupIds;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getMuscularGroupId() {
        return muscularGroupId;
    }

    public void setMuscularGroupId(Integer muscularGroupId) {
        this.muscularGroupId = muscularGroupId;
    }

    public void save() {
        MuscularGroup mg = stc.find(MuscularGroup.class, muscularGroupId.toString());
        SpecificTip st = new SpecificTip();
        st.setMuscularGroupId(mg);
        st.setText(text);
        stc.create(st);
        addMessage("Data saved");

    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
