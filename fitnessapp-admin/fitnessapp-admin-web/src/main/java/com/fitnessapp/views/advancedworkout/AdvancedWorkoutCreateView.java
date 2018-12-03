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

import com.fitnessapp.api.client.AdvancedWorkoutClient;
import com.fitnessapp.api.client.BodyTypeClient;
import com.fitnessapp.api.entities.AdvancedWorkout;
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

@Named(value = "advancedWorkoutCreateView")
@ViewScoped
public class AdvancedWorkoutCreateView implements Serializable {

    private Integer bodyTypeId;
    private List<Integer> btypeIds;
    private AdvancedWorkoutClient bwc;
    private AdvancedWorkout bw = new AdvancedWorkout();
    private List<AdvancedWorkout> bwl = new ArrayList<AdvancedWorkout>();

    @PostConstruct
    public void init() {
        bwc = new AdvancedWorkoutClient();
        btypeIds = getBtypeIds();
    }

    public List<Integer> getBtypeIds() {
        BodyTypeClient bClient = new BodyTypeClient();
        List<BodyType> tmpBtype = bClient.findAll(new GenericType<List<BodyType>>() {
        });
        List<Integer> tmpIds = new ArrayList<>();
        for (BodyType t : tmpBtype) {
            tmpIds.add(t.getId());
        }
        return tmpIds;
    }

    public void setBtypeIds(List<Integer> btypeIds) {
        this.btypeIds = btypeIds;
    }

    public Integer getBodyTypeId() {
        return bodyTypeId;
    }

    public void setBodyTypeId(Integer bodyTypeId) {
        this.bodyTypeId = bodyTypeId;
    }

    public void save() {
        BodyTypeClient btc = new BodyTypeClient();
        BodyType bt = btc.find(BodyType.class, bodyTypeId.toString());
        AdvancedWorkout bwf = new AdvancedWorkout();
        bwf.setBodyTypeId(bt);
        bwc.create(bwf);
        addMessage("Data saved");
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
