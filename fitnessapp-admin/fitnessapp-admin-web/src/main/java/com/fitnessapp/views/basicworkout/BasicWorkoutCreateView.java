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
import com.fitnessapp.api.entities.BasicWorkout;
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

@Named(value = "basicWorkoutCreateView")
@ViewScoped
public class BasicWorkoutCreateView implements Serializable {

    private Integer bodyTypeId;
    private List<Integer> btypeIds;
    private BasicWorkoutClient bwc;
    private BasicWorkout bw = new BasicWorkout();
    private List<BasicWorkout> bwl = new ArrayList<BasicWorkout>();

    @PostConstruct
    public void init() {
        bwc = new BasicWorkoutClient();
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
        BasicWorkout bwf = new BasicWorkout();
        bwf.setBodyTypeId(bt);
        bwc.create(bwf);
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
