/*
 * Copyright (C) 2018 Jordi
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
package com.fitnessapp.views;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fitnessapp.api.client.MuscularGroupClient;
import com.fitnessapp.api.entities.MuscularGroup;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.core.GenericType;
import org.primefaces.json.JSONObject;

/**
 *
 * @author Jordi
 */
@Named(value = "muscularGroupCreateView")
@ViewScoped
public class MuscularGroupCreateView implements Serializable {

    private String muscularGroupName;
    private MuscularGroupClient muscularGroupClient = new MuscularGroupClient();

    private MuscularGroup muscularGroup = new MuscularGroup();
    private List<MuscularGroup> mg = new ArrayList<MuscularGroup>();

    @PostConstruct
    public void init() {
    }

    public String getMuscularGroupName() {
        return muscularGroupName;
    }

    public void setMuscularGroupName(String muscularGroupName) {
        this.muscularGroupName = muscularGroupName;
    }

    public void save() {

        muscularGroup.setMuscularGroupName(muscularGroupName);
        if (muscularGroupName != null) {

            muscularGroupClient.create(muscularGroup);
            
            addMessage("MuscularGroup Added");
        }
    }

    public MuscularGroupClient getMuscularGroupClient() {
        return muscularGroupClient;
    }

    public void setMuscularGroupClient(MuscularGroupClient muscularGroupClient) {
        this.muscularGroupClient = muscularGroupClient;
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public MuscularGroup getMuscularGroup() {
        return muscularGroup;
    }

    public void setMuscularGroup(MuscularGroup muscularGroup) {
        this.muscularGroup = muscularGroup;
    }

}
