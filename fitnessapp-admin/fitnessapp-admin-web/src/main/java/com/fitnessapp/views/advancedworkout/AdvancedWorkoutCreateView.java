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

@Named(value = "advancedWorkoutCreateView")
@ViewScoped
public class AdvancedWorkoutCreateView implements Serializable {

    private Integer bodyTypeId;

    private AdvancedWorkoutClient awc = new AdvancedWorkoutClient();

    private AdvancedWorkout aw = new AdvancedWorkout();
    private List<AdvancedWorkout> awl = new ArrayList<AdvancedWorkout>();

    @PostConstruct
    public void init() {
    }

    public Integer getBodyTypeId() {
        return bodyTypeId;
    }

    public void setBodyTypeId(Integer bodyTypeId) {
        this.bodyTypeId = bodyTypeId;
    }

    public void save() {
        aw.setBodyTypeId(bodyTypeId);
        if (bodyTypeId != 0) {

            awc.create(aw);

            addMessage("AdvancedWorkout Added");
        }
    }

    public AdvancedWorkoutClient getAdvancedWorkoutClient() {
        return awc;
    }

    public void setAdvancedWorkoutClient(AdvancedWorkoutClient awc) {
        this.awc = awc;
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
