/*
 * Copyright (C) 2018 Erox
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

import com.fitnessapp.api.entities.Survey;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Erox
 */
@Named(value = "surveyAddRowView")
@ViewScoped
public class SurveyAddRowView implements Serializable{

    private List<Survey> surveys;
    
    @PostConstruct
    public void init(){
        surveys = new ArrayList<>();
        Survey survey = new Survey(1,"Test Survey", 1);
        Survey survey2 = new Survey(2,"Test Survey", 2);
        surveys.add(survey);
        surveys.add(survey2);
        surveys.add(survey);
        surveys.add(survey);
        surveys.add(survey);
        surveys.add(survey);
        surveys.add(survey);
        surveys.add(survey);
    }
    
    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Survey Edited", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onAddNew() {
        FacesMessage msg = new FacesMessage("New Survey added", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void delete() {
        FacesMessage msg = new FacesMessage("Data Deleted", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
